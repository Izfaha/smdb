package com.donasibanjir.sistem_donasi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.donasibanjir.sistem_donasi.model.Bencana;

import org.springframework.ui.Model;

@Controller
public class ViewController {

    // routing index
    @GetMapping("/")
    public String index(Model model) {
        // Data statistik dummy untuk menyuplai halaman utama (index.html)
        model.addAttribute("totalDonatur", 2500);
        model.addAttribute("totalWilayah", 15);

        // Simulasi data bencana (nantinya ditarik dari BencanaRepository)
        List<Bencana> daftarBencana = new ArrayList<>();
        daftarBencana.add(new Bencana(1L, "Banjir Rendam 2 Kecamatan di Tanah Datar", "Sumatera Barat", 150000000.0));
        daftarBencana.add(new Bencana(2L, "Luapan Sungai Bengawan Solo", "Surakarta, Jawa Tengah", 200000000.0));
        daftarBencana.add(new Bencana(3L, "Banjir Bandang Pemukiman Warga", "Semarang Utara", 75000000.0));

        model.addAttribute("daftarBencana", daftarBencana);
        return "index";
    }
}
