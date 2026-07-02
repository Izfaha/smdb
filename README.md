# Sistem Donasi Banjir 

**Sistem Donasi Banjir** adalah platform berbasis web monolitik sederhana yang dirancang khusus untuk menjembatani para donatur dalam menyalurkan bantuan dana secara instan, transparan, dan efisien kepada korban bencana banjir di berbagai wilayah terdampak tanpa melalui alur birokrasi panel admin yang rumit. 

Proyek ini dibangun sebagai pemenuhan Tugas Akhir Praktikum **Pemrograman Berorientasi Objek (PBO) Java** dengan menerapkan pilar-pilar OOP murni, integrasi database relasional, serta komponen kecerdasan buatan (AI).

---

## 🛠️ Tech Stack & Ekosistem
* **Backend:** Java 17, Spring Boot 3.x
* **Frontend:** Thymeleaf Template Engine, Bootstrap 5
* **Database:** PostgreSQL 15
* **DevOps & Environment:** Docker, Docker Compose

---

## Penerapan Konsep PBO & Fitur Utama

Aplikasi ini mengintegrasikan seluruh pilar fundamental Pemrograman Berorientasi Objek (PBO) ke dalam arsitektur backend Spring Boot melalui empat fitur utama:

### 1. Manajemen Pengguna (Konsep *Inheritance*)
* **Deskripsi:** Sistem memisahkan data kredensial dasar akun dengan data profil spesifik pengguna tanpa menciptakan duplikasi kode (*code redundancy*).
* **Implementasi:** Terdapat sebuah *superclass* bernama `User` yang merangkum atribut autentikasi dasar (`id`, `username`, `email`, `password`). Kelas ini kemudian diwariskan menggunakan kata kunci `extends` kepada *subclass* murni bernama `Donatur` yang diperkaya dengan atribut profil tambahan seperti `nomorTelepon` dan `alamat`.

### 2. Manajemen Wilayah Terdampak & Dana (Konsep *Encapsulation*)
* **Deskripsi:** Melindungi integritas data bencana banjir dari manipulasi luar yang tidak sah.
* **Implementasi:** Seluruh variabel penting di dalam kelas entitas seperti `id`, `namaBencana`, `targetDonasi`, `danaTerkumpul`, hingga kumpulan daftar wilayah yang bertipe data `ArrayList` dideklarasikan menggunakan akses modifier `private`. Interaksi data dijembatani secara aman melalui fungsi *Getter* dan *Setter* murni (*Accessor* dan *Mutator*).

### 3. Transaksi Donasi & Simulasi Pembayaran (Konsep *Polymorphism*)
* **Deskripsi:** Menangani variasi simulasi metode pembayaran yang dipilih oleh donatur secara dinamis pada saat *runtime*.
* **Implementasi:** Memanfaatkan kontrak *Interface* bernama `MetodePembayaran` dengan fungsi abstrak `prosesPembayaran(Double nominal)`. Prinsip polimorfisme bekerja ketika fungsi ini diimplementasikan secara adaptif oleh cabang sub-kelas nyata yaitu `TransferBank` (dengan properti `nomorRekening`) dan `EWallet` (dengan properti `nomorEwallet`). Setelah data valid, sistem mengirimkan respons yang memicu kemunculan *notifikasi pop-up modal* sukses beranimasi di halaman utama.

### 4. AI Virtual Assistant / Chatbot Widget (Integrasi AI/LLM)
* **Deskripsi:** Fitur asisten virtual pintar untuk meningkatkan interaktivitas pengguna langsung dari halaman publik.
* **Implementasi:** Ditampilkan dalam bentuk komponen tombol lingkaran melayang (*floating button*) di pojok kiri bawah halaman utama (`index.html`). Ketika diklik, jendela obrolan (*chatbot*) akan terbuka dan terhubung secara *asynchronous* melalui Controller Java backend menuju eksternal LLM API (seperti Gemini/OpenAI). AI bertugas memberikan respons *real-time* seputar panduan donasi, informasi logistik, hingga rekomendasi wilayah darurat banjir terparah yang paling membutuhkan bantuan.

---

## Struktur Dokumen & Alur Data Java

Penataan kode backend dan frontend dipisah secara modular ke dalam struktur folder Spring Boot murni untuk memudahkan kolaborasi tim di GitHub:

* `src/main/resources/images/diagram.png` : Diagram app.
* `src/main/java/.../model/` : Representasi objek murni PBO dan JPA Entity (`User`, `Donatur`, `Bencana`, `Donasi`).
* `src/main/java/.../repository/` : Jembatan otomatis Spring Data JPA ke database PostgreSQL tanpa SQL manual.
* `src/main/java/.../controller/` : Pengatur rute lalu lintas data web (`ViewController` dan `DonasiController`).
* `src/main/resources/templates/` : Berkas tampilan UI dinamis berbasis Thymeleaf (`index.html` dan `form-donasi.html`).
* `src/main/resources/static/` : Aset statis seperti CSS stlyling dan gambar (`images/foto-1.jpg`, `images/foto-2.jpg`).

---

## Cara Menjalankan Proyek (How to Run)

Pastikan kamu sudah memasang **Docker**, **Docker Compose**, dan **Java 17 (OpenJDK)** di sistem operasi kamu.

### Clone Repositori
```bash
git clone https://github.com/Izfaha/smdb.git sistem-donasi
cd sistem-donasi
docker compose up --build
```

Lalu buka `localhost:8084` untuk FE dan untuk database `port 5433`.