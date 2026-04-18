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
| *(screenshot)* | *(screenshot)* | *(screenshot)* |

### 2. Map & Welcome Screen
| Map Screen | Welcome Indonesia | Welcome Malaysia |
|:---:|:---:|:---:|
| *(screenshot)* | *(screenshot)* | *(screenshot)* |

### 3. Login & Register
| Login | Register |
|:---:|:---:|
| *(screenshot)* | *(screenshot)* |

### 4. Home & Detail Buku
| Home (All) | Home (Pendidikan) | Home (Novel) | Detail Buku |
|:---:|:---:|:---:|:---:|
| *(screenshot)* | *(screenshot)* | *(screenshot)* | *(screenshot)* |

### 5. Peminjaman & Pengembalian
| Pinjam Buku | Sukses Pinjam | Pengembalian | Scan Barcode |
|:---:|:---:|:---:|:---:|
| *(screenshot)* | *(screenshot)* | *(screenshot)* | *(screenshot)* |

### 6. Riwayat & Rak Buku
| Sedang Dipinjam | Riwayat | Rak Buku |
|:---:|:---:|:---:|
| *(screenshot)* | *(screenshot)* | *(screenshot)* |

### 7. AI Chatbot Bookie & Profil
| Bookie AI | Profil |
|:---:|:---:|
| *(screenshot)* | *(screenshot)* |

---

## ✨ Fitur yang ditambahkan

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

## ⚙️ Cara Menjalankan Project

1. Clone repository ini:
```bash
git clone https://github.com/amelianurmala02-arch/Pinbook.git
```

2. Buka dengan **Android Studio**

3. Tambahkan file `google-services.json` dari Firebase Console ke folder `app/`

4. Ganti API key Gemini di `AiChatActivity.kt`:
```kotlin
val apiKey = "YOUR_GEMINI_API_KEY"
```

5. Run project ke emulator atau device Android (min SDK 23)

---

## 🔗 Tautan Penting

| Platform | Link |
|----------|------|
| **Repository GitHub** | [github.com/amelianurmala02-arch/Pinbook](https://github.com/amelianurmala02-arch/Pinbook) |
| **Video Demo** | *(isi link video kamu)* |
| **ClickUp SCRUM** | *(isi link ClickUp kamu)* |
| **MEGAH-EDLINK** | [megah.pelitabangsa.ac.id](https://megah.pelitabangsa.ac.id/) |

---

Project ini dibuat untuk keperluan UTS Mata Kuliah Pemrograman Mobile 2 — Universitas Pelita Bangsa Semester Genap 2025/2026.
