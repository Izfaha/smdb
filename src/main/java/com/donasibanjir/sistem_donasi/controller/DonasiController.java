package com.donasibanjir.sistem_donasi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; 

import com.donasibanjir.sistem_donasi.model.Bencana;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class DonasiController {
    
    // routing donasi
    @GetMapping("/donasi")
    public String tampilkanFormDonasi(@RequestParam(value = "bencanaId", required = false) Long bencanaId, Model model) {
        // Menangkap ID bencana jika user mengklik tombol bencana spesifik dari index.html
        model.addAttribute("bencanaIdTerpilih", bencanaId);

        // Menyiapkan daftar pilihan bencana untuk dropdown pilihan di form donasi 
        List<Bencana> daftarBencana = new ArrayList<>();
        daftarBencana.add(new Bencana(1L, "Banjir Rendam 2 Kecamatan di Tanah Datar", "Sumatera Barat", 150000000.0));
        daftarBencana.add(new Bencana(2L, "Luapan Sungai Bengawan Solo", "Surakarta, Jawa Tengah", 200000000.0));
        daftarBencana.add(new Bencana(3L, "Banjir Bandang Pemukiman Warga", "Semarang Utara", 75000000.0));
        
        model.addAttribute("daftarBencana", daftarBencana);

        return "form-donasi"; 
    }

    @PostMapping("/donasi/kirim") 
    public String prosesDonasi(
            @RequestParam("nama") String nama,
            @RequestParam("nomorTelepon") String nomorTelepon,
            @RequestParam("bencanaId") Long bencanaId,
            @RequestParam("nominalDonasi") Double nominalDonasi,
            @RequestParam("metodePembayaran") String metodePembayaran,
            RedirectAttributes redirectAttributes) { 
        
        // Cetak log data ke console/terminal [cite: 221]
        System.out.println("=========================================");
        System.out.println("DONASI BARU MASUK (POST MOCK DATA)");
        System.out.println("Nama Donatur     : " + nama);
        System.out.println("No HP            : " + nomorTelepon);
        System.out.println("ID Target Bencana: " + bencanaId);
        System.out.println("Nominal Donasi   : Rp " + nominalDonasi);
        System.out.println("Metode Bayar     : " + metodePembayaran);
        System.out.println("=========================================");
        
        // Mengirimkan data sukses secara aman di belakang layar (Flash Attributes) 
        redirectAttributes.addFlashAttribute("sukses", true);
        redirectAttributes.addFlashAttribute("namaDonatur", nama);
        redirectAttributes.addFlashAttribute("nominalDonasi", nominalDonasi);

        // Mengalihkan kembali ke halaman utama (Landing Page) 
        return "redirect:/"; 
    }
}