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

<img width="1920" height="1080" alt="Group 127" src="https://github.com/user-attachments/assets/ce247760-2d47-4025-8b1b-b3d71b2c199d" />

> Mockup di atas menampilkan seluruh halaman aplikasi Pinbook sebelum diimplementasikan.

---

## 📱 UI Aplikasi (Screenshot Asli)

### 1. Splash Screen & Location Permission
| Splash Screen | Location Permission | Detecting Location |
|:---:|:---:|:---:|
|<img width="412" height="917" alt="Splash Screen 2" src="https://github.com/user-attachments/assets/3d887bdf-5396-4e50-9a30-9bb8750c8465" />| <img width="412" height="917" alt="Android Compact - 1" src="https://github.com/user-attachments/assets/6f649983-65b6-4bf2-8d50-9a5715fa4a6a" /> | <img width="413" height="917" alt="Detecting Location Screen" src="https://github.com/user-attachments/assets/8d640718-ab69-4baa-b7f3-cf3dc4e1bb87" /> |

### 2. Welcome Screen & Login
| Welcome Indonesia | Login | 
|:---:|:---:|
|<img width="412" height="917" alt="Welcome Screen" src="https://github.com/user-attachments/assets/710d1b8c-f7f2-4525-9e54-179a4352ab33" /> | <img width="412" height="917" alt="Login Screen" src="https://github.com/user-attachments/assets/1d9c41ff-a5da-4f2d-b82d-76e1aa5d6c2f" /> |

### 3. Register & Home
| Register | Home |
|:---:|:---:|
| <img width="412" height="917" alt="Register Screen" src="https://github.com/user-attachments/assets/091b3630-ca40-4f5d-893d-bbda013e4580" /> | <img width="412" height="917" alt="Group 113 (1)" src="https://github.com/user-attachments/assets/24fe8d9b-cbbd-4ceb-9a00-022c0539295e" /> |

### 4. Detail Buku  & Peminjaman
| Detail Buku | Pinjam Buku | Sukses Pinjam |
|:---:|:---:|:---:|
| <img width="412" height="917" alt="Book Detail 2" src="https://github.com/user-attachments/assets/002d903e-bf9d-4dd2-939e-33ebfb59fa85" /> | <img width="412" height="917" alt="Calendar Picker 2" src="https://github.com/user-attachments/assets/f77b6f3b-d842-44e8-a291-033ca87f23db" /> | <img width="412" height="917" alt="Return Confirmation Screen" src="https://github.com/user-attachments/assets/b60e131f-f7d4-47f3-aed8-af5f9d771c85" /> | 

### 5. Pengembalian & Riwayat
| Pengembalian | Scan Barcode | Sedang Dipinjam | Riwayat | 
|:---:|:---:|:---:|:---:|
|<img width="412" height="917" alt="Return Book Screen" src="https://github.com/user-attachments/assets/611e9dec-924f-483e-9d65-4796b51d5ee0" /> | <img width="412" height="917" alt="Return Confirmation Screen (1)" src="https://github.com/user-attachments/assets/4b846ac3-0e36-43b4-a371-f1f084e59b0b" /> | <img width="412" height="917" alt="Borrowing History Screen 1" src="https://github.com/user-attachments/assets/98219b0a-4391-40d6-b792-5c6029cf9b8c" /> | <img width="412" height="917" alt="Borrowing History Screen 2" src="https://github.com/user-attachments/assets/6178433e-d34a-4593-9e63-5ae503cf42a3" /> |

### 6. Rak Buku, AI & Profil
| Rak Buku | Bookie AI |  Profil |
|:---:|:---:|:---:|
| <img width="412" height="917" alt="Book Rack Screen" src="https://github.com/user-attachments/assets/8bb5a764-11c6-4e95-9a45-8ebcc995a5ef" /> | <img width="412" height="927" alt="Group 114" src="https://github.com/user-attachments/assets/55ae0445-e1d9-40aa-93cb-6e5fff13c220" /> | <img width="412" height="917" alt="Profile Screen" src="https://github.com/user-attachments/assets/0ae78fd9-78df-4f19-a525-4e0590a7b35c" /> |


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
