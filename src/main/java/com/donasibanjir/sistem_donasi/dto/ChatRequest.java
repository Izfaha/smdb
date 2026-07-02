package com.donasibanjir.sistem_donasi.dto;

/**
 * DTO (Data Transfer Object) untuk menampung pesan yang dikirim
 * dari widget chatbot (frontend) menuju endpoint /api/chatbot/pesan.
 */
public class ChatRequest {

    private String pesan;

    public ChatRequest() {
    }

    public ChatRequest(String pesan) {
        this.pesan = pesan;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
}