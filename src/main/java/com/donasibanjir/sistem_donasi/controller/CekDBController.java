package com.donasibanjir.sistem_donasi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CekDBController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/cek-db")
    public String cekKoneksiDatabase(Model model) {
        String statusDB;
        String alertClass;

        try {
            Integer hasil = jdbcTemplate.queryForObject("SELECT 1", Integer.class);

            if (hasil != null && hasil == 1) {
                statusDB = "Koneksi Sukses! SpringBoot berhasil connect PostgreSQL.";
                alertClass = "alert-success";
            } else {
                statusDB = "Database merespon, tetapi mengembalikan data yang tidak sesuai.";
                alertClass = "alert-warning";
            }
        } catch (Exception e) {
            statusDB = "Gagal terhubung ke database! Error: " + e.getMessage();
            alertClass = "alert-danger";
        }

        model.addAttribute("statusDB", statusDB);
        model.addAttribute("allertClass", alertClass);

        return "cek-db";
    }
}
