# 📚 Pinbook — Aplikasi Perpustakaan Digital Berbasis Android

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

## 🎥 Video UX / Demo Aplikasi

[![Video Demo Pinbook](https://img.shields.io/badge/▶%20Tonton%20Demo-YouTube-red?style=for-the-badge&logo=youtube)](LINK_VIDEO_KAMU_DI_SINI)

---

## Storyboard

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

---

## Mockup UI

![Mockup Pinbook](mockup.jpg)

> Mockup di atas menampilkan seluruh halaman aplikasi Pinbook sebelum diimplementasikan.

---

## 📱 UI Aplikasi (Screenshot Asli)

### 1. Splash Screen & Location Permission
| Splash Screen | Location Permission | Detecting Location |
|:---:|:---:|:---:|
|![A4 - 1 (1)](https://github.com/user-attachments/assets/5502f8a8-7c71-494d-9fb8-104ed2b6a55a) | ![A4 - 2 (1)](https://github.com/user-attachments/assets/53c91296-3686-4ab3-999f-3e232881d6cd) | ![A4 - 3 (1)](https://github.com/user-attachments/assets/8e28118e-74d2-4003-abf0-ced9d774e2d0)|

### 2. Welcome Screen & Login
| Welcome Indonesia | Login | 
|:---:|:---:|
|![A4 - 4 (1)](https://github.com/user-attachments/assets/f9c6ff9e-abb8-44b1-87d8-e717f1b2a1c2)| ![A4 - 5 (1)](https://github.com/user-attachments/assets/7ee6c888-5cd7-4ea3-bc04-0504a6651dbd) |

### 3. Register & Home
| Register | Home |
|:---:|:---:|
| ![A4 - 6 (1)](https://github.com/user-attachments/assets/7391601b-603d-4419-a68a-7bf140baceba) | <img width="595" height="842" alt="A4 - 7" src="https://github.com/user-attachments/assets/9ca62ac6-ae75-4b09-bc95-d1cf8884d92a" /> |

### 4. Detail Buku  & Peminjaman
| Detail Buku | Pinjam Buku | Sukses Pinjam |
|:---:|:---:|:---:|
| ![A4 - 8 (1)](https://github.com/user-attachments/assets/e1242195-fbb3-4f23-95a4-b361c8ea3e33) | ![A4 - 9 (1)](https://github.com/user-attachments/assets/e3aa7f77-0bc5-45f4-8567-7800bd74c6be) | <img width="595" height="842" alt="A4 - 11" src="https://github.com/user-attachments/assets/4ab5d4c1-f614-4c5b-9b26-0259360ba8de" /> | 

### 5. Pengembalian & Riwayat
| Pengembalian | Scan Barcode | Sedang Dipinjam | Riwayat | 
|:---:|:---:|:---:|:---:|
| <img width="595" height="842" alt="A4 - 13" src="https://github.com/user-attachments/assets/cb5b54f6-10ba-4310-bf85-12c45c3ef357" /> | *(screenshot)* | ![A4 - 15 (1)](https://github.com/user-attachments/assets/825e6588-78f0-45c9-95f6-061e0994648f) | ![A4 - 16 (1)](https://github.com/user-attachments/assets/66806fa5-5ef5-4521-b9ba-c50cbed223bd) |

### 6. Rak Buku, AI & Profil
| Rak Buku | Bookie AI |  Profil |
|:---:|:---:|:---:|
| *(screenshot)* | *(screenshot)* | ![A4 - 17 (1)](https://github.com/user-attachments/assets/f7b952c9-e7f6-4020-b42a-1a612cae9f02)|


---

## Fitur yang ditambahkan

### 1. Kategori Buku
- Filter buku berdasarkan kategori: **All**, **Pendidikan**, **Novel**
- Pencarian buku berdasarkan judul

### 2. Scan Barcode Pengembalian Buku
- Menggunakan library ZXing untuk scan barcode
- Status buku otomatis berubah dari "Dipinjam" → "Dikembalikan"
- Buku otomatis pindah ke tab Riwayat

### 3. Notifikasi Pengingat Pengembalian
- Notifikasi langsung saat berhasil meminjam buku
- Notifikasi pengingat H-2 sebelum tanggal kembali
- Notifikasi pengingat H-1 sebelum tanggal kembali
- Notifikasi pengingat pada hari H pengembalian
- Dijadwalkan setiap hari jam 8 pagi menggunakan WorkManager

### 4. AI Chatbot Bookie
- Chatbot berbasis Gemini API
- Memberikan rekomendasi buku sesuai minat pengguna
- Mengenali koleksi buku yang tersedia di Pinbook

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
| **Video Demo** | *(isi link video kamu)* |
| **ClickUp SCRUM** | *(isi link ClickUp kamu)* |
| **MEGAH-EDLINK** | [megah.pelitabangsa.ac.id](https://megah.pelitabangsa.ac.id/) |

---

Project ini dibuat untuk keperluan UTS Mata Kuliah Pemrograman Mobile 2 — Universitas Pelita Bangsa Semester Genap 2025/2026.
