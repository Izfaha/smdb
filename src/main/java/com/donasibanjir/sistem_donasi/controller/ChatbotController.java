package com.donasibanjir.sistem_donasi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.donasibanjir.sistem_donasi.dto.ChatRequest;
import com.donasibanjir.sistem_donasi.dto.ChatResponse;
import com.donasibanjir.sistem_donasi.service.ChatbotService;


@RestController
public class ChatbotController {

    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping("/api/chatbot/pesan")
    public ChatResponse kirimPesan(@RequestBody ChatRequest request) {
        String pesan = request.getPesan() == null ? "" : request.getPesan().trim();

        if (pesan.isEmpty()) {
            return new ChatResponse("Silakan tulis pertanyaan kamu terlebih dahulu ya.");
        }

        String balasan = chatbotService.balas(pesan);
        return new ChatResponse(balasan);
    }
}