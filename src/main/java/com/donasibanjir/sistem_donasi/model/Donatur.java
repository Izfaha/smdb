package com.donasibanjir.sistem_donasi.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "donatur")
public class Donatur {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;
    private String nomorTelepon;
    private Double nominalDonasi;
    private String metodePembayaran;
    private LocalDateTime tanggalDonasi;

    // Relasi Banyak Donatur ke Satu Target Bencana (PBO OOP Association)
    @ManyToOne
    @JoinColumn(name = "bencana_id", nullable = false)
    private Bencana bencana;

    // Konstruktor kosong untuk JPA
    public Donatur() {}

    // Konstruktor berparameter lengkap untuk merepresentasikan objek donatur baru
    public Donatur(String nama, String nomorTelepon, Double nominalDonasi, String metodePembayaran, Bencana bencana) {
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.nominalDonasi = nominalDonasi;
        this.metodePembayaran = metodePembayaran;
        this.bencana = bencana;
        this.tanggalDonasi = LocalDateTime.now(); // Tanggal donasi tercatat otomatis saat objek dibuat
    }

    // Getter dan Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public Double getNominalDonasi() {
        return nominalDonasi;
    }

    public void setNominalDonasi(Double nominalDonasi) {
        this.nominalDonasi = nominalDonasi;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public LocalDateTime getTanggalDonasi() {
        return tanggalDonasi;
    }

    public void setTanggalDonasi(LocalDateTime tanggalDonasi) {
        this.tanggalDonasi = tanggalDonasi;
    }

    public Bencana getBencana() {
        return bencana;
    }

    public void setBencana(Bencana bencana) {
        this.bencana = bencana;
    }
}
