package com.amelia.pinbook

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amelia.pinbook.adapter.BukuAdapter
import com.amelia.pinbook.manager.FavoriteManager
import com.amelia.pinbook.model.Buku
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.widget.Toast
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest

class HomeActivity : AppCompatActivity() {

    lateinit var adapter: BukuAdapter
    lateinit var searchInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        val settingsPrefs = getSharedPreferences("Settings", MODE_PRIVATE)
        val lang = settingsPrefs.getString("language", "in") ?: "in"
        val locale = java.util.Locale(lang)
        java.util.Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences("pinbook_pref", MODE_PRIVATE)
        val isLoggedIn = prefs.getBoolean("is_logged_in", false)

        if (!isLoggedIn) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_home)

        // 🔴 INISIALISASI FAVORITEMANAGER (TAMBAHKAN INI!)
        FavoriteManager.init(this)

        // Minta izin notifikasi (Android 13+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    101
                )
            }
        }

        // Jadwalkan notifikasi pengingat
        BookReminderScheduler.schedule(this)

        val recycler = findViewById<RecyclerView>(R.id.recyclerBuku)
        searchInput = findViewById(R.id.searchInput)

        // ===========================
        // LIST BUKU UNTUK DITAMPILKAN
        // ===========================
        val listBuku = arrayListOf(
            Buku(
                id = "1",
                judul = "Pemrograman Berbasis Mobile",
                deskripsi = "Buku ini merupakan buku ajar dari mata kuliah Komputasi Bergerak pada jurusan D3 Manajemen Informatika Fakultas Teknik Universitas Trunojoyo Madura, namun agar dapat dijadikan referensi bagi peminat mobile programing maka judul buku ini dibuat lebih umum. Buku yang berjudul pemrograman berbasis mobile menggunakan android studio disajikan dalam format tutorial yang mudah dipahami dari pengenalan awal tentang dasar pemrograman android sampai pada tahapan mempublish aplikasi di playstore. Semua listing program pada buku ini telah diuji coba pada Android Studio menggunakan SDK versi 24. Buku ini terdiri dari 12 bab, bab pertama akan mengupas lengkap bab android dan sistemnya, bab 2 adalah instalasi pemogramannya, kemudian bab selanjutnya secra berurutan akan membahas layout, widget android, activity dan intent, menu dan layanan android, audio dan video, penyimpanan data, location based, dan yang terakhir adalah testing dan publishing aplikasi.",
                gambar = R.drawable.book1,
                stok = 3,
                isFavorite = false,
                kategori = "Pendidikan"
            ),
            Buku(
                id = "2",
                judul = "Pemrograman Web",
                deskripsi = "Buku ini memberikan panduan esensial yang membimbing pemula melalui perjalanan mendalam pengembangan web, Dimulai dengan memahami HTML sebagai tulang punggung halaman web, pembaca akan diajak merinci struktur dokumen, menyematkan gambar, tautan, dan formulir, serta memahami semantik HTML untuk meningkatkan SEO. Setelah itu, panduan menyeluruh tentang CSS akan mempercantik tampilan halaman web, membahas dari penggunaan selektor hingga animasi. Kemudian, JavaScript menghidupkan halaman web dengan pembahasan variabel, perulangan, dan manipulasi DOM, memungkinkan pembaca membangun interaktivitas yang kuat.",
                gambar = R.drawable.book2,
                stok = 2,
                isFavorite = false,
                kategori = "Pendidikan"
            ),
            Buku(
                id = "3",
                judul = "Rekayasa Perangkat Lunak",
                deskripsi = "Pada buku ini akan membahas tentang analisis dan desain sistem dengan disertai suatu studi kasus untuk memudahkan dalam pemahaman. Analisis dan desain sistem itu dimulai dengan analisis dan desain basis data, analisis dan desain sistem untuk pemrograman terstruktur dengan menggunakan DFD, dan analisis dan desain sistem untuk pemrograman berorientasi objek dengan menggunakan UML.",
                gambar = R.drawable.book3,
                stok = 5,
                isFavorite = false,
                kategori = "Pendidikan"
            ),
            Buku(
                id = "4",
                judul = "Jaringan Komputer",
                deskripsi = "Buku ini menjelaskan cara membuat jaringan komputer .Pada buku ini Anda akan dikenalkan denagan berbagai aspek seoutar jaringan komoputer, dituntun cara membuatnya sendiri, dan memilih model jaringan komputer yang tepat untuk kebutuhan Anda. Selain itu, di bagian akhir jiga akan disajikan contoh-contoh penerapan jaringan komputer untuk usaha warung internet, game online, serta usaha print dan copy center.",
                gambar = R.drawable.book4,
                stok = 1,
                isFavorite = false,
                kategori = "Pendidikan"
            ),
            Buku(
                id = "5",
                judul = "Probabilitas dan Statiska",
                deskripsi = "Buku Statistika Probabilitas ini ditulis dengan bahasa yang sederhana dan telah disesuaikan dengan satuan acara perkuliahan (SAP). Buku ini berisi pelajaran statistika probabilitas di mana pada setiap bab selalu diisi dengan pendahuluan untuk mengantarkan dan mengarahkan pikiran para pembaca guna memahami konsep yang akan dibahas, diikuti penanaman konsep, pendalaman konsep pada contoh soal yang disertai dengan pembahasan, dan dilengkapi kasus untuk diskusi.",
                gambar = R.drawable.book5,
                stok = 4,
                isFavorite = false,
                kategori = "Pendidikan"
            ),
            Buku(
                id = "6",
                judul = "Kalkulus",
                deskripsi = "Kalkulus Diferensial dan Integral sebagai cabang keilmuan berperan penting sebagai dasar ilmu pengetahuan yang mendukung keahlian dalam bidang matematika lanjutan dan bidang keteknikan. Selain itu, juga merupakan mata kuliah utama yang mengantarkan mahasiswa supaya dapat memahami cabang-cabang matematika tingkat tinggi. Sebagai mata kuliah keahlian dasar, Kalkulus Diferensial dan Integral harus dipelajari oleh mahasiswa pada jurusan Pendidikan Matematika, Fakultas Teknik, Fakultas Ekonomi, Fakultas MIPA-Matematika, Fakultas Teknik Informatika, dan ilmu-ilmu komputer lainnya di setiap perguruan tinggi.",
                gambar = R.drawable.book6,
                stok = 3,
                isFavorite = false,
                kategori = "Pendidikan"
            ),

            // BUKU NOVEL
            Buku(
                id = "7",
                judul = "Mariposa",
                deskripsi = "Novel Mariposa menceritakan tentang Natasha Kay Loovi (Acha), siswi SMA yang cerdas dan berprestasi, terutama dalam pelajaran Kimia. Ia jatuh hati pada Iqbal, siswa tampan yang dikenal dingin, cuek, dan sulit didekati. Kisah mereka bermula dari pertemuan di camp olimpiade. Meskipun memiliki tingkat kecerdasan yang seimbang, Acha harus menghadapi tantangan besar untuk mendapatkan perhatian Iqbal. Dengan bantuan dan dukungan dari sahabat-sahabatnya seperti Amanda, Rian, dan Glen, Acha terus berusaha mendekati dan meluluhkan hati Iqbal.Di balik perjuangan cintanya, cerita ini juga menggambarkan arti persahabatan, proses pendewasaan, serta keberanian untuk tetap memperjuangkan perasaan meskipun banyak rintangan.",
                gambar = R.drawable.book_mariposa,
                stok = 3,
                kategori = "Novel"
            ),
            Buku(
                id = "8",
                judul = "Kisah Untuk Geri",
                deskripsi = "Kisah ini bermula saat kehidupan dinda yang berubah 180 derajat setelah ayahnya ditangkap oleh KPK karena melakukan kasus korupsi. Dia yang awalnya hidup serba mewah, dipaksa keluar dari rumah dan menjalani kehidupan baru di rumah kontrakan kecil. Tak hanya itu ia juga di bully di sekolahan oleh teman-temannya, serta ia juga dimusuhin oleh beberapa teman dekatnya. Dia bergabung dalam salah satu geng nya yaitu The Satan, yang memiliki fisik sempurna, dan ayah yang mempunyai pengaruh kuat. Serta dinda dulu sering melakukan hal yang semena-mena, dan banyak anak sekolah yang tidak menyukasi sifatnya. Saat itulah ia berada dititik terendahnya, ia jatuh miskin dan temannya membully. Agar tetap bertahan di sekolahnya ia berniatan untuk menjadi pacar Geri, salah satu pria yang ditakuti di sekolah. Ia berusaha untuk mendapatkan cinta geri walaupun itu tak mudah.",
                gambar = R.drawable.book_kisah_untuk_geri,
                stok = 2,
                kategori = "Novel"
            ),
            Buku(
                id = "9",
                judul = "Teluk Alaska",
                deskripsi = "Novel Teluk Alaska menceritakan Ana, gadis pendiam yang sering dirundung dan mencurahkan perasaannya lewat diary. Hidupnya berubah saat ia bertemu Bulan, teman baru yang membuatnya lebih ceria. Ana juga bertemu Alister, teman masa kecilnya yang kini berubah menjadi kasar. Namun setelah mengetahui Alister diam-diam menyimpan luka dan kesedihan, Ana mulai memahami sisi lain dirinya. Cerita ini mengangkat tentang masa lalu, luka batin, dan kemungkinan memperbaiki hubungan yang pernah hilang.",
                gambar = R.drawable.book_teluk_alaska,
                stok = 4,
                kategori = "Novel"
            ),
            Buku(
                id = "10",
                judul = "Samuel",
                deskripsi = "Sinopsis Novel Samuel karya Ita Kurniawati mengisahkan kehidupan tokoh utama yang bernama Samuel Erlangga. Tokoh ini juga sering dipanggil dengan nama Samuel. Azura memanggilnya dengan sebutan Baby El. Tokoh Samuel dikenal sebagai sosok pria tampan yang berkepribadian tegas, tidak baperan, dan kejam. Tetapi, sebenarnya sifat tersebut berbanding terbalik dengan sifatnya yang sebenarnya. Di kehidupan nyatanya, saat berada di rumah, Samuel adalah sosok anak yang manja, apalagi saat sudah bertemu dengan susu di botol spiderman buatan ibunya. Tokoh Samuel memang selalu menyembunyikan kehidupan aslinya dibalik topeng. Di depan banyak orang, Samuel terlihat mempunyai kehidupan yang sempurna.",
                gambar = R.drawable.book_samuel,
                stok = 2,
                kategori = "Novel"
            ),
            Buku(
                id = "11",
                judul = "Dignitate",
                deskripsi = "Alfi, lelaki ganteng dengan segala kemampuan yang ia punya; genius, jagoan, disegani banyak orang, dan mampu menaklukkan banyak hati perempuan. Sayangnya, ia terkenal galak. Selalu membuat orang sakit hati dengan ucapannya; sarkatis, pedas, penuh kecaman dan menohok. Dia juga terkenal sadis, apalagi dengan orang-orang yang mengganggu ketenangannya. ‘Si Ganteng yang bengis’, itulah julukan yang diberikan pada sosok pemilik nama lengkap Genta Denalfian. Tetapi, Alfi punya satu kekurangan, dia belum pernah pacaran. Belum merasakan pahit dan manisnya jatuh cinta. Hanya Alana Caroline yang mampu menghancurkan pertahanan Alfi. Namun, Alfi terlalu gengsi untuk menyatakan perasaan pada Alana. Ada peristiwa tragis yang mengancam nyawa Alana. Selain itu, ada laki-laki lain yang juga menyimpan perasaan seperti apa yang Alfi rasakan pada Alana. Apakah Alfi masih memiliki kesempatan untuk menyatakan cinta ke Alana?",
                gambar = R.drawable.book_dignitate,
                stok = 3,
                kategori = "Novel"
            ),
            Buku(
                id = "12",
                judul = "Dilan",
                deskripsi = "Cerita dimulai saat Milea pindah ke Bandung dan bertemu Dilan. Berbeda dengan remaja lain, Dilan mendekati Milea dengan cara unik dan gombalan maut. Hubungan mereka berlanjut ke konflik ringan khas remaja hingga konflik berat (terutama di buku kedua, Dilan 1991).",
                gambar = R.drawable.book_dilan,
                stok = 5,
                kategori = "Novel"
            )

        )


        // ===========================
        // ADAPTER
        // ===========================
        adapter = BukuAdapter(
            listBuku,
            onFavoriteClick = { buku ->
                // Cek status favorite dari FavoriteManager
                val currentlyFavorite = FavoriteManager.isFavorite(buku)

                if (currentlyFavorite) {
                    FavoriteManager.removeFavorite(buku)
                } else {
                    FavoriteManager.addFavorite(buku)
                }

                val total = FavoriteManager.getFavorites().size
                Toast.makeText(this, "Total favorite: $total", Toast.LENGTH_SHORT).show()

                adapter.notifyDataSetChanged()
            },
            onItemClick = { buku ->
                val i = Intent(this, BookDetailActivity::class.java)
                i.putExtra("buku", buku)
                startActivity(i)
            }
        )

        // ===========================
        // RECYCLER VIEW
        // ===========================
        recycler.layoutManager = GridLayoutManager(this, 2)
        recycler.adapter = adapter

        // ===========================
        // FILTER KATEGORI
        // ===========================
        val chipAll = findViewById<com.google.android.material.chip.Chip>(R.id.chipAll)
        val chipPendidikan = findViewById<com.google.android.material.chip.Chip>(R.id.chipPendidikan)
        val chipNovel = findViewById<com.google.android.material.chip.Chip>(R.id.chipNovel)

        chipAll.setOnClickListener {
            chipAll.isChecked = true
            chipPendidikan.isChecked = false
            chipNovel.isChecked = false
            adapter.filterByKategori("All")
        }

        chipPendidikan.setOnClickListener {
            chipAll.isChecked = false
            chipPendidikan.isChecked = true
            chipNovel.isChecked = false
            adapter.filterByKategori("Pendidikan")
        }

        chipNovel.setOnClickListener {
            chipAll.isChecked = false
            chipPendidikan.isChecked = false
            chipNovel.isChecked = true
            adapter.filterByKategori("Novel")
        }

        // ===========================
        // SEARCH
        // ===========================
        searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // ===========================
        // PROFILE ICON
        // ===========================
        findViewById<ImageView>(R.id.btnProfile).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        // ===========================
        // BOTTOM NAVIGATION
        // ===========================
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_home

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true

                R.id.nav_shelf -> {
                    startActivity(Intent(this, BookRackActivity::class.java))
                    true
                }

                R.id.nav_ai -> {
                    startActivity(Intent(this, AiChatActivity::class.java))
                    true
                }

                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }

                else -> false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}