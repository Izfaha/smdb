package com.donasibanjir.sistem_donasi.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ArrayNode;
import tools.jackson.databind.node.ObjectNode;


@Service
public class ChatbotService {

    @Value("${groq.api.key:}")
    private String apiKey;

    @Value("${groq.api.url:https://api.groq.com/openai/v1/chat/completions}")
    private String apiUrl;

    @Value("${groq.api.model:llama-3.3-70b-versatile}")
    private String model;

    private static final String SYSTEM_INSTRUCTION = """
            Kamu adalah "PeduliBot", asisten virtual di website donasi bencana banjir
            bernama "Peduli Banjir". Tugasmu membantu pengunjung website dengan:
            - Menjelaskan cara berdonasi di website ini (klik tombol "Donasi Sekarang" / menu Donasi,
              isi nama, nomor HP, pilih bencana yang ingin dibantu, masukkan nominal, pilih metode
              pembayaran, lalu kirim).
            - Memberi informasi umum seputar bencana banjir yang sedang ditampilkan di halaman utama.
            - Menjawab pertanyaan umum seputar donasi dan kebencanaan.
            Jawab selalu dalam Bahasa Indonesia, dengan gaya ramah, sopan, singkat, dan jelas.
            Jika pertanyaan di luar topik donasi/bencana, tetap jawab dengan singkat tapi arahkan
            kembali ke topik website bila relevan.
            """;

    private final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String balas(String pesanPengguna) {
        if (apiKey == null || apiKey.isBlank()) {
            return "Maaf, chatbot belum dikonfigurasi. Admin website perlu mengisi "
                    + "GROQ_API_KEY terlebih dahulu.";
        }

        try {
            return panggilGroqApi(pesanPengguna);
        } catch (Exception e) {
            System.out.println("[PeduliBot] Gagal memanggil Groq API: " + e.getMessage());
            return "Maaf, PeduliBot sedang mengalami gangguan. Silakan coba lagi beberapa saat lagi.";
        }
    }

    private String panggilGroqApi(String pesanPengguna) throws Exception {
        ObjectNode root = objectMapper.createObjectNode();
        root.put("model", model);

        ArrayNode messages = root.putArray("messages");

        ObjectNode systemMsg = messages.addObject();
        systemMsg.put("role", "system");
        systemMsg.put("content", SYSTEM_INSTRUCTION);

        ObjectNode userMsg = messages.addObject();
        userMsg.put("role", "user");
        userMsg.put("content", pesanPengguna);

        String requestBody = objectMapper.writeValueAsString(root);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .timeout(Duration.ofSeconds(20))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            System.out.println("[PeduliBot] Groq API status " + response.statusCode() + ": " + response.body());
            return "Maaf, PeduliBot belum bisa menjawab sekarang. Silakan coba lagi nanti.";
        }

        JsonNode json = objectMapper.readTree(response.body());
        JsonNode textNode = json.path("choices").path(0).path("message").path("content");

        if (textNode.isMissingNode() || textNode.asText().isBlank()) {
            return "Maaf, PeduliBot belum mendapat jawaban yang jelas. Bisa dicoba tanyakan dengan cara lain?";
        }

        return textNode.asText().trim();
    }
}