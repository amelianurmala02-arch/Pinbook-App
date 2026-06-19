# Pinbook — Aplikasi Perpustakaan Digital Berbasis Android

*Nama         : Amelia Nurmala Dewi*

*NIM         : 312410199* 

*Kelas       : I241B*

*Mata Kuliah : Pemrograman Mobile 2*

*Dosen       : Donny Maulana, S.Kom., M.M.S.I.*

*Universitas Universitas Pelita Bangsa*

---

<img width="595" height="421" alt="A4 - 21" src="https://github.com/user-attachments/assets/5e018e53-8b1c-41c6-91e5-894868c4118b" />

---
Pinbook adalah aplikasi perpustakaan digital berbasis Android yang memudahkan pengguna dalam meminjam, mengembalikan, dan mengelola buku secara online. Dilengkapi dengan fitur AI Chatbot, Scan Barcode, Notifikasi Pengingat, dan Multi-Language sesuai lokasi pengguna.


---

## Latar Belakang

Banyak mahasiswa yang kesulitan dalam meminjam dan mengelola buku perpustakaan secara manual. Proses pencatatan yang masih konvensional seringkali menyebabkan kehilangan data, keterlambatan pengembalian buku, dan sulitnya mencari buku yang sesuai kebutuhan. Selain itu, proses pengembalian manual juga rawan human error karena petugas harus mencatat satu per satu data buku yang dikembalikan.

Oleh karena itu, dikembangkan Pinbook sebagai solusi digital yang memudahkan proses peminjaman dan pengembalian buku. Pinbook berfokus murni pada pengelolaan peminjaman buku (bukan aplikasi baca buku/e-reader), sehingga pengguna dapat melihat detail buku, meminjam, dan mengembalikan buku hanya dengan scan barcode tanpa perlu input data secara manual.

---

## Tujuan Pengembangan

Mempermudah pencatatan peminjaman dan pengembalian buku perpustakaan secara digital
Mengurangi risiko kehilangan data akibat pencatatan manual
Membantu pengguna agar tidak terlambat mengembalikan buku melalui notifikasi pengingat otomatis
Mempercepat proses pencarian buku melalui fitur kategori dan pencarian
Memberikan kemudahan rekomendasi buku melalui AI Chatbot sesuai minat pengguna
Mempercepat proses pengembalian buku tanpa input manual, cukup dengan scan barcode

---

## 🎥 Video UX / Demo Aplikasi

[![Video Demo Pinbook](https://img.shields.io/badge/▶%20Tonton%20Demo-YouTube-red?style=for-the-badge&logo=youtube)](https://youtu.be/7bhAccqndLA?si=6OTrp3IFaHA6w1ds)

---

## Storyboard

<img width="1920" height="1080" alt="Desktop - 1" src="https://github.com/user-attachments/assets/d50b573c-cecd-432a-9be1-3d8173cda99c" />

<img width="1920" height="1080" alt="Desktop - 2" src="https://github.com/user-attachments/assets/871d0932-fb27-4a8d-8e43-f5622a2bc827" />

Alur penggunaan aplikasi Pinbook:

```
[Splash Screen]
      ↓
[Location Permission Screen]
   ↙           ↘
[Izin OK]    [Izin Tolak]
   ↓               ↓
[Detecting    [Welcome Screen]
 Location]    (Bahasa Inggris +
   ↓           Bendera Dunia)
[Map Screen]
   ↓
[Welcome Screen]
(Bendera + Bahasa sesuai lokasi)
   ↓
[Login Screen]
   ↓
[Home Screen]
   ↓         ↓         ↓         ↓
[Detail    [Rak     [AI Chat  [Profil]
  Buku]    Buku]   Bookie]
   ↓         ↓
[Pinjam  [Pengembalian
 Buku]    Buku]
   ↓         ↓
[Sukses] [Scan Barcode]
             ↓
          [Sukses]
             ↓
       [Riwayat Pinjaman]
```
## Penjelasan Alur Aplikasi

1. Splash Screen — Tampilan pembuka saat aplikasi pertama kali dibuka.
2. Location Permission Screen — Pengguna diminta mengizinkan akses lokasi.
   - Jika izin diberikan → aplikasi mendeteksi lokasi pengguna otomatis (Detecting Location → Map Screen) untuk menyesuaikan bahasa dan        bendera negara.
   - Jika izin ditolak → pengguna diarahkan ke Welcome Screen dengan bahasa default Inggris.
3. Welcome Screen — Menampilkan bendera dan bahasa sesuai lokasi yang terdeteksi (mendukung 8 bahasa).
4. Login / Register — Pengguna masuk menggunakan akun terdaftar, atau mendaftar akun baru via Firebase Authentication.
5. Home Screen — Halaman utama berisi daftar buku, filter kategori, dan akses ke fitur lain (Rak Buku, AI Chat Bookie, Profil).
6. Detail Buku — Pengguna dapat melihat informasi lengkap buku sebelum memutuskan untuk meminjam.
7. Pinjam Buku — Pengguna memilih tanggal pengembalian lewat kalender, lalu konfirmasi peminjaman.
8. Pengembalian Buku — Pengguna membuka halaman pengembalian, lalu scan barcode buku.
9. Riwayat Pinjaman — Semua data peminjaman (aktif maupun sudah dikembalikan) tercatat dan dapat dilihat kembali oleh pengguna.

---

## Mockup UI

<img width="1920" height="1080" alt="Desktop - 4 (1)" src="https://github.com/user-attachments/assets/b1259249-895c-488d-bf5f-6750a6ebd85f" />
<img width="1920" height="1080" alt="Group 127 (1)" src="https://github.com/user-attachments/assets/8c6876c5-fa67-417f-99ed-262787a2ee8d" />

> Mockup di atas menampilkan seluruh halaman aplikasi Pinbook sebelum diimplementasikan.

---

## 📱 UI Aplikasi (Screenshot Asli)

### 1. Splash Screen & Location Permission
| Splash Screen | Location Permission |
|:---:|:---:|
|<img width="412" height="917" alt="Splash Screen 2" src="https://github.com/user-attachments/assets/3d887bdf-5396-4e50-9a30-9bb8750c8465" />| <img width="412" height="917" alt="Android Compact - 1" src="https://github.com/user-attachments/assets/6f649983-65b6-4bf2-8d50-9a5715fa4a6a" /> |

### 2. Detecting Location &  Welcome Screen 
| Detecting Location | Welcome Indonesia | 
|:---:|:---:|
|<img width="413" height="917" alt="Detecting Location Screen" src="https://github.com/user-attachments/assets/8d640718-ab69-4baa-b7f3-cf3dc4e1bb87" /> | <img width="412" height="917" alt="Welcome Screen (1)" src="https://github.com/user-attachments/assets/27e5012a-21be-4a26-8320-5626c9ce4be4" /> |

### 3. Login & Register 
| Login | Register| 
|:---:|:---:|
| <img width="412" height="917" alt="Register Screen" src="https://github.com/user-attachments/assets/091b3630-ca40-4f5d-893d-bbda013e4580" /> | <img width="412" height="917" alt="Register Screen (1)" src="https://github.com/user-attachments/assets/6d10e7d0-ccec-4c1f-8c73-fb3344d14554" /> | 

### 4. Home & Detail Buku
| Home | Detail Buku | 
|:---:|:---:|
| <img width="412" height="917" alt="Home Screen3" src="https://github.com/user-attachments/assets/3e2334db-391f-4baa-be25-e026ed5a9e1e" /> |<img width="412" height="917" alt="Book Detail 2 (1)" src="https://github.com/user-attachments/assets/295162d9-13f0-4f4b-b539-a7a99ea6c9f5" /> | 

### 5. Konfirmasi Peminajaman & Peminjaman Berhasil
| Konfirmasi Peminajaman  | Peminjaman Berhasil | 
|:---:|:---:|
| <img width="412" height="917" alt="Borrow Book Screen 1" src="https://github.com/user-attachments/assets/a36ad7c5-7011-42ff-9edd-feb95e73d7a9" /> | <img width="412" height="917" alt="Return Confirmation Screen (2)" src="https://github.com/user-attachments/assets/e3fcf77e-de7c-4ec6-9d45-956a03bb181a" /> | 

### 6. Rak Buku & Pengembalian Buku
| Rak Buku | Pengembalian Buku | 
|:---:|:---:|
| <img width="412" height="917" alt="Book Rack Screen (1)" src="https://github.com/user-attachments/assets/b9c1c73b-1156-4937-85e7-905a2f2c3032" /> | <img width="412" height="917" alt="Return Book Screen (1)" src="https://github.com/user-attachments/assets/3d1f2081-28d7-4872-8957-dd1dc1dee361" /> | 

### 7. Scan Barcode & Pengembalian Berhasil
| Scan Barcode | Pengembalian Berhasil |
|:---:|:---:|
| <img width="412" height="917" alt="Return Confirmation Screen (3)" src="https://github.com/user-attachments/assets/291d928e-0710-470b-8b34-bed82c8ec4e2" /> | <img width="412" height="917" alt="Borrow Success Screen" src="https://github.com/user-attachments/assets/d07fc9bd-7608-47b8-9372-9c43bbf09706" /> | 

### 8. Riwayat & Favorite
| Riwayat | Favorite |
|:---:|:---:|
| <img width="412" height="917" alt="Borrowing History Screen 1 (1)" src="https://github.com/user-attachments/assets/f5369908-b7fc-480b-ac92-288ddbe84201" /> | <img width="412" height="917" alt="Favorite Screen" src="https://github.com/user-attachments/assets/f0fe9570-f8ec-46ba-8c93-5b1ad91e0d89" /> | 

### 9. Profil & AI
| Profil | AI |
|:---:|:---:|
| <img width="412" height="917" alt="Profile Screen (1)" src="https://github.com/user-attachments/assets/3e7cc010-e66f-41aa-8497-92ca857571b2" /> | <img width="412" height="927" alt="AI Screen" src="https://github.com/user-attachments/assets/ac69c81a-966c-4a5a-852e-b09ce087c3ff" /> | 

---

## Fitur yang ditambahkan

### 1. Kategori Buku
- Filter buku berdasarkan kategori: **All**, **Pendidikan**, **Novel**
- Pencarian buku berdasarkan judul
  
Cara kerja: Pengguna cukup menekan salah satu chip kategori (All, Pendidikan, atau Novel) di Home Screen, dan daftar buku akan otomatis tersaring sesuai kategori yang dipilih tanpa perlu reload halaman. Dengan fitur ini, pengguna tidak perlu scroll panjang untuk mencari buku yang diinginkan.

### 2. Scan Barcode Pengembalian Buku
- Menggunakan library ZXing untuk scan barcode
- Status buku otomatis berubah dari "Dipinjam" → "Dikembalikan"
- Buku otomatis pindah ke tab Riwayat

Cara kerja: Pengguna membuka halaman Pengembalian Buku, lalu menekan tombol scan dan mengarahkan kamera ke barcode yang tertera pada buku fisik. Sistem akan otomatis mencocokkan kode buku dengan data peminjaman yang tersimpan, lalu mengubah status peminjaman tanpa perlu input manual sama sekali.

### 3. Notifikasi Pengingat Pengembalian
- Notifikasi langsung saat berhasil meminjam buku
- Notifikasi pengingat H-2 sebelum tanggal kembali
- Notifikasi pengingat H-1 sebelum tanggal kembali
- Notifikasi pengingat pada hari H pengembalian
- Dijadwalkan setiap hari jam 8 pagi menggunakan WorkManager

Cara kerja: Setiap kali pengguna berhasil meminjam buku, BookReminderScheduler akan menjadwalkan 3 notifikasi pengingat (H-2, H-1, dan hari-H) menggunakan WorkManager. Notifikasi akan otomatis muncul setiap pukul 8 pagi sesuai jadwal yang ditentukan, sehingga pengguna tidak akan lupa mengembalikan buku tepat waktu.

### 4. AI Chatbot Bookie
- Chatbot berbasis Gemini API
- Memberikan rekomendasi buku sesuai minat pengguna
- Mengenali koleksi buku yang tersedia di Pinbook

Cara kerja: Pengguna cukup mengetikkan genre atau preferensi buku yang diinginkan pada kolom chat (misalnya "aku suka cerita romantis" atau "rekomendasi buku pemrograman"). Bookie akan memproses permintaan menggunakan Gemini API dan merekomendasikan buku yang benar-benar tersedia di koleksi Pinbook, dijawab secara ramah dalam Bahasa Indonesia.

---

## Teknologi yang Digunakan

| Teknologi | Kegunaan |
|-----------|----------|
| **Kotlin** | Bahasa pemrograman utama Android |
| **Firebase Auth** | Autentikasi pengguna (Login/Register) |
| **Firebase Firestore** | Database cloud |
| **Room Database** | Database lokal (data peminjaman) |
| **ZXing Library** | Scan barcode pengembalian buku |
| **WorkManager** | Penjadwalan notifikasi pengingat |
| **Gemini API** | AI Chatbot Bookie |
| **Google Maps/Location** | Deteksi lokasi pengguna |
| **OpenStreetMap (Leaflet)** | Peta interaktif pilih lokasi manual |
| **Geocoder/Nominatim** | Konversi koordinat ke nama negara |

---

## Struktur Project

```
Pinbook/
├── app/
│   ├── src/main/
│   │   ├── java/com/amelia/pinbook/
│   │   │   ├── adapter/          # BukuAdapter, BorrowingAdapter, ChatAdapter
│   │   │   ├── data/             # Room Database, DAO, Entity
│   │   │   ├── manager/          # FavoriteManager
│   │   │   ├── model/            # Buku, ChatMessage
│   │   │   ├── utils/            # StringHelper
│   │   │   ├── AiChatActivity
│   │   │   ├── BookDetailActivity
│   │   │   ├── BorrowBookActivity
│   │   │   ├── BorrowingHistoryActivity
│   │   │   ├── DetectingLocationActivity
│   │   │   ├── HomeActivity
│   │   │   ├── LocationPermissionActivity
│   │   │   ├── LoginActivity
│   │   │   ├── MapActivity
│   │   │   ├── NotificationHelper
│   │   │   ├── BookReminderWorker
│   │   │   ├── BookReminderScheduler
│   │   │   ├── ProfileActivity
│   │   │   ├── RegisterActivity
│   │   │   ├── ReturnBookActivity
│   │   │   ├── ScanQrActivity
│   │   │   └── WelcomeActivity
│   │   ├── res/
│   │   │   ├── layout/           # XML layout semua halaman
│   │   │   ├── values/           # strings.xml (Inggris)
│   │   │   ├── values-in/        # strings.xml (Indonesia)
│   │   │   ├── values-ms/        # strings.xml (Malaysia)
│   │   │   ├── values-ja/        # strings.xml (Jepang)
│   │   │   ├── values-fr/        # strings.xml (Perancis)
│   │   │   ├── values-de/        # strings.xml (Jerman)
│   │   │   ├── values-ko/        # strings.xml (Korea)
│   │   │   └── values-zh/        # strings.xml (China)
│   │   └── assets/
│   │       └── map.html          # Peta interaktif Leaflet
```

---

## Tautan Penting

| Platform | Link |
|----------|------|
| **Repository GitHub** | [github.com/amelianurmala02-arch/Pinbook](https://github.com/amelianurmala02-arch/Pinbook) |
| **Video UX** | https://youtu.be/7bhAccqndLA?si=6OTrp3IFaHA6w1ds |
| **Video App** | https://youtube.com/shorts/Wuz8url4_UA?si=jdRiZdLGeozrdF6N |
| **ClickUp SCRUM** | https://sharing.clickup.com/90181767922/g/h/2kzm14qj-678/2b3374f74beb58e |

---
