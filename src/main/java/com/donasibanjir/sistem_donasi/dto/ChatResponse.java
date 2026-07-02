package com.donasibanjir.sistem_donasi.dto;

/**
 * DTO untuk membungkus balasan chatbot yang dikirim kembali ke frontend.
 */
public class ChatResponse {

    private String balasan;

    public ChatResponse() {
    }

    public ChatResponse(String balasan) {
        this.balasan = balasan;
    }

    public String getBalasan() {
        return balasan;
    }

    public void setBalasan(String balasan) {
        this.balasan = balasan;
    }
}