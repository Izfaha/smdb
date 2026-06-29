package com.donasibanjir.sistem_donasi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bencana")
public class Bencana {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namaBencana;
    private String daerahTerdampak;
    private Double targetDonasi;

    // Konstruktor kosong wajib untuk spesifikasi JPA Entity
    public Bencana() {}

    // Konstruktor berparameter untuk kemudahan instansiasi objek
    public Bencana(Long id, String namaBencana, String daerahTerdampak, Double targetDonasi) {
        this.id = id;
        this.namaBencana = namaBencana;
        this.daerahTerdampak = daerahTerdampak;
        this.targetDonasi = targetDonasi;
    }

    // Implementasi Enkapsulasi PBO: Getter dan Setter yang aman
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaBencana() {
        return namaBencana;
    }

    public void setNamaBencana(String namaBencana) {
        this.namaBencana = namaBencana;
    }

    public String getDaerahTerdampak() {
        return daerahTerdampak;
    }

    public void setDaerahTerdampak(String daerahTerdampak) {
        this.daerahTerdampak = daerahTerdampak;
    }

    public Double getTargetDonasi() {
        return targetDonasi;
    }

    public void setTargetDonasi(Double targetDonasi) {
        this.targetDonasi = targetDonasi;
    }
}
