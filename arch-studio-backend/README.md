# 🏛️ Arch Studio - Backend API

<div align="center">

**Platform RESTful API untuk Sistem Informasi Company Profile Arch Studio**

*Membangun jembatan antara konten dinamis dan tampilan website yang profesional*

[![Java](https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-brightgreen?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16+-blue?style=for-the-badge&logo=postgresql)](https://www.postgresql.org/)
[![Gradle](https://img.shields.io/badge/Gradle-9.5-darkgreen?style=for-the-badge&logo=gradle)](https://gradle.org/)
[![License](https://img.shields.io/badge/Lisensi-MIT-yellow?style=for-the-badge)](LICENSE)

</div>

---

## 📋 Deskripsi Proyek

**Arch Studio Backend** adalah RESTful API yang dibangun menggunakan **Java 25** dan **Spring Boot 4.1.0** untuk mendukung sistem *company profile* studio arsitektur dan desain interior **MDK (Mitra Daya Kreasi)**. API ini melayani seluruh kebutuhan data dinamis halaman website, mulai dari manajemen portofolio proyek, lowongan kerja, hingga pengaturan konten halaman oleh admin.

### ✨ Tujuan
Memungkinkan tim admin untuk mengelola seluruh konten website secara *real-time* melalui dashboard admin tanpa perlu menyentuh kode sama sekali, dengan data yang tersimpan permanen di database PostgreSQL.

---

## 🛠️ Tech Stack

| Komponen | Teknologi | Versi |
|---|---|---|
| Bahasa Pemrograman | Java | 25 |
| Framework | Spring Boot | 4.1.0 |
| Database | PostgreSQL | 16+ |
| ORM | Hibernate (via Spring Data JPA) | 7.4.x |
| Build Tool | Gradle | 9.5.x |
| Keamanan | JWT (jjwt) | 0.12.5 |
| Password Hashing | BCrypt (jbcrypt) | 0.4 |
| Boilerplate Reducer | Lombok | latest |
| JSON Processing | Jackson Databind | latest |
| Dependency Management | Spring Dependency Management Plugin | 1.1.7 |

---

## 📁 Struktur Kode

```
arch-studio-backend/
├── src/
│   ├── main/
│   │   ├── java/com/mitradayakreasi/backend/
│   │   │   │
│   │   │   ├── ArchStudioBackendApplication.java   # Entry point aplikasi Spring Boot
│   │   │   │
│   │   │   ├── modules/                            # Fitur utama aplikasi (per domain bisnis)
│   │   │   │   ├── about/                          # Modul halaman "Tentang Kami"
│   │   │   │   ├── career/                         # Modul halaman "Lowongan Karir"
│   │   │   │   │   ├── CareerSettings.java         # Entity pengaturan halaman karir
│   │   │   │   │   ├── CareerItem.java             # DTO untuk item kartu potensi/budaya
│   │   │   │   │   ├── CareerItemListConverter.java # JPA Converter List<CareerItem> <-> JSON
│   │   │   │   │   ├── JobOpening.java             # Entity lowongan kerja
│   │   │   │   │   ├── JobApplication.java         # Entity lamaran kerja
│   │   │   │   │   └── ...Repository/Service/Controller.java
│   │   │   │   ├── contact/                        # Modul pesan kontak
│   │   │   │   ├── home/                           # Modul pengaturan Home Banner
│   │   │   │   ├── message/                        # Modul pesan masuk
│   │   │   │   ├── project/                        # Modul portofolio desain
│   │   │   │   │   ├── Project.java                # Entity proyek dengan metadata & galeri
│   │   │   │   │   ├── CreateProjectRequest.java   # DTO untuk buat proyek baru
│   │   │   │   │   ├── UpdateProjectRequest.java   # DTO untuk update proyek
│   │   │   │   │   └── ...Repository/Service/Controller.java
│   │   │   │   ├── serviceitem/                    # Modul halaman "Layanan"
│   │   │   │   │   ├── ServiceItem.java            # Entity item layanan
│   │   │   │   │   ├── WorkProcess.java            # Entity metode kerja
│   │   │   │   │   └── ...Repository/Service/Controller.java
│   │   │   │   └── user/                           # Modul autentikasi admin
│   │   │   │       ├── User.java                   # Entity pengguna admin
│   │   │   │       ├── JwtService.java             # Layanan generate & validasi JWT
│   │   │   │       └── ...Repository/Service/Controller.java
│   │   │   │
│   │   │   ├── model/                              # Model respons umum
│   │   │   │   ├── ApiResponse.java                # Wrapper standar respons API (data, message, status)
│   │   │   │   └── WebResponse.java                # Wrapper alternatif (untuk error handler)
│   │   │   │
│   │   │   └── shared/                             # Komponen lintas-modul (cross-cutting concerns)
│   │   │       ├── AuthInterceptor.java            # Interceptor validasi JWT untuk endpoint protected
│   │   │       ├── GlobalExceptionHandler.java     # Handler error global (400, 401, 404, 500)
│   │   │       └── WebConfig.java                  # Konfigurasi CORS & pendaftaran interceptor
│   │   │
│   │   └── resources/
│   │       └── application.yaml                    # Konfigurasi datasource, JPA, dan server
│   │
│   └── test/                                       # Unit dan integration tests
│
├── build.gradle                                    # Definisi dependencies & plugin Gradle
├── gradlew                                         # Gradle wrapper (Linux/Mac)
├── gradlew.bat                                     # Gradle wrapper (Windows)
└── settings.gradle                                 # Nama project Gradle
```

---

## 🗄️ Skema Database (Tabel Utama)

| Tabel | Deskripsi |
|---|---|
| `users` | Data akun admin (username, password hash) |
| `projects` | Data portofolio desain (beserta galeri & metadata detail) |
| `project_gallery_images` | Tabel relasi gambar galeri per proyek (ElementCollection) |
| `project_kategori` | Tabel relasi kategori per proyek (ElementCollection) |
| `services` | Daftar layanan studio (judul, deskripsi, icon) |
| `work_processes` | Daftar langkah metode kerja studio |
| `career_settings` | Pengaturan konten halaman karir (hero, potentials, cultures) |
| `job_openings` | Daftar lowongan kerja yang tersedia |
| `job_applications` | Lamaran kerja yang masuk dari calon karyawan |
| `about_us` | Konten halaman Tentang Kami (deskripsi, visi, misi) |
| `home_banners` | Data hero banner halaman utama |

---

## 🌊 Alur Kerja Sistem

```
Pengguna/Admin (Browser)
        │
        │  HTTP Request (JSON)
        ▼
┌─────────────────────────────────────┐
│         AuthInterceptor             │  ◄── Validasi JWT Token
│  (Middleware sebelum Controller)    │
└─────────────────────────────────────┘
        │
        │  Request diteruskan (jika valid)
        ▼
┌─────────────────────────────────────┐
│           Controller Layer          │  ◄── Menerima HTTP Request
│    (misal: ProjectController)       │      Memanggil Service
└─────────────────────────────────────┘
        │
        │  Panggilan metode
        ▼
┌─────────────────────────────────────┐
│            Service Layer            │  ◄── Logika bisnis (validasi, transformasi)
│     (misal: ProjectService)         │      Memanggil Repository
└─────────────────────────────────────┘
        │
        │  Operasi CRUD
        ▼
┌─────────────────────────────────────┐
│          Repository Layer           │  ◄── Interface JPA (extends JpaRepository)
│   (misal: ProjectRepository)        │      Otomatis diimplementasikan Spring Data
└─────────────────────────────────────┘
        │
        │  SQL Query (via Hibernate ORM)
        ▼
┌─────────────────────────────────────┐
│         PostgreSQL Database         │  ◄── Penyimpanan data permanen
└─────────────────────────────────────┘
        │
        │  Data dikembalikan sebagai objek Java
        ▼
  Respons JSON (ApiResponse<T>)
  Dikembalikan ke Pengguna/Admin
```

---

## 🔐 Sistem Autentikasi

API ini menggunakan **JWT (JSON Web Token)** untuk melindungi endpoint admin:

- **Endpoint publik (tanpa token)**: `GET /api/projects`, `GET /api/services`, `GET /api/career/settings`, `POST /api/applications`, dsb.
- **Endpoint protected (wajib token)**: Semua operasi `POST`, `PUT`, `DELETE` pada data konten admin.
- Token JWT dikirimkan melalui header `Authorization: Bearer <token>` pada setiap request ke endpoint protected.

---

## 📡 Daftar Endpoint API

### Autentikasi
| Method | Endpoint | Akses | Deskripsi |
|---|---|---|---|
| `POST` | `/api/auth/login` | Publik | Login admin, mendapatkan JWT token |

### Portofolio Proyek
| Method | Endpoint | Akses | Deskripsi |
|---|---|---|---|
| `GET` | `/api/projects` | Publik | Ambil semua proyek |
| `GET` | `/api/projects/{id}` | Publik | Ambil detail satu proyek |
| `POST` | `/api/projects` | 🔐 Admin | Tambah proyek baru |
| `PUT` | `/api/projects/{id}` | 🔐 Admin | Update data proyek |
| `DELETE` | `/api/projects/{id}` | 🔐 Admin | Hapus proyek |

### Layanan & Metode Kerja
| Method | Endpoint | Akses | Deskripsi |
|---|---|---|---|
| `GET` | `/api/services` | Publik | Ambil semua layanan |
| `POST` | `/api/services` | 🔐 Admin | Tambah layanan baru |
| `PUT` | `/api/services/{id}` | 🔐 Admin | Update layanan |
| `DELETE` | `/api/services/{id}` | 🔐 Admin | Hapus layanan |
| `GET` | `/api/services/processes` | Publik | Ambil semua metode kerja |
| `POST` | `/api/services/processes` | 🔐 Admin | Tambah metode kerja |
| `PUT` | `/api/services/processes/{id}` | 🔐 Admin | Update metode kerja |
| `DELETE` | `/api/services/processes/{id}` | 🔐 Admin | Hapus metode kerja |

### Karir & Lowongan
| Method | Endpoint | Akses | Deskripsi |
|---|---|---|---|
| `GET` | `/api/career/settings` | Publik | Ambil pengaturan halaman karir |
| `PUT` | `/api/career/settings` | 🔐 Admin | Update pengaturan halaman karir |
| `GET` | `/api/jobs` | Publik | Ambil semua lowongan kerja |
| `POST` | `/api/jobs` | 🔐 Admin | Tambah lowongan baru |
| `PUT` | `/api/jobs/{id}` | 🔐 Admin | Update lowongan |
| `DELETE` | `/api/jobs/{id}` | 🔐 Admin | Hapus lowongan |
| `POST` | `/api/applications` | Publik | Kirim lamaran kerja |
| `GET` | `/api/applications` | 🔐 Admin | Lihat semua lamaran masuk |

---

## 🚀 Cara Instalasi & Menjalankan

### Prasyarat

Pastikan perangkat Anda telah menginstal:
- **Java JDK 25+** — [Download di sini](https://openjdk.org/)
- **PostgreSQL 16+** — [Download di sini](https://www.postgresql.org/download/)
- **Git** — [Download di sini](https://git-scm.com/)

### Langkah 1: Clone Repository

```bash
git clone https://github.com/[username]/arch-studio-backend.git
cd arch-studio-backend
```

### Langkah 2: Siapkan Database PostgreSQL

Buka `psql` atau GUI (seperti pgAdmin) dan jalankan:

```sql
CREATE DATABASE db_mitradayakreasi;
```

### Langkah 3: Konfigurasi Koneksi Database

Buka file `src/main/resources/application.yaml` dan sesuaikan kredensial database Anda:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/db_mitradayakreasi
    username: postgres        # Ganti dengan username PostgreSQL Anda
    password: admin           # Ganti dengan password PostgreSQL Anda
  jpa:
    hibernate:
      ddl-auto: update        # Hibernate akan otomatis membuat/update tabel
```

> **Catatan:** Dengan konfigurasi `ddl-auto: update`, seluruh tabel database akan **dibuat secara otomatis** oleh Hibernate pada saat pertama kali aplikasi dijalankan. Anda tidak perlu menjalankan skrip SQL secara manual.

### Langkah 4: Jalankan Aplikasi

**Menggunakan Gradle Wrapper (Direkomendasikan):**

```bash
# Windows
./gradlew.bat bootRun

# Linux / Mac
./gradlew bootRun
```

**Atau build terlebih dahulu lalu jalankan:**

```bash
./gradlew build
java -jar build/libs/arch-studio-backend-0.0.1-SNAPSHOT.jar
```

### Langkah 5: Verifikasi

Aplikasi berjalan sukses jika Anda melihat output berikut di terminal:

```
Started ArchStudioBackendApplication in X.XXX seconds
```

API siap diakses di: `http://localhost:8080`

---

## ⚙️ Fitur Utama

### 1. 🔐 Autentikasi Admin Berbasis JWT
Sistem login yang aman menggunakan JSON Web Token. Token berumur terbatas dan dikirimkan melalui header HTTP untuk memproteksi seluruh endpoint pengelolaan konten.

### 2. 🖼️ Manajemen Portofolio Proyek
CRUD lengkap untuk data proyek arsitektur, termasuk dukungan menyimpan gambar dalam format Base64 (tipe kolom `TEXT`), galeri gambar multi-foto (`@ElementCollection`), dan metadata detail seperti nama klien, lokasi, tahun, serta material yang digunakan.

### 3. 👔 Sistem Lowongan & Lamaran Kerja
Endpoint publik bagi pengunjung untuk mengirim lamaran kerja beserta CV dalam format Base64. Admin dapat melihat daftar seluruh lamaran masuk beserta statusnya melalui dashboard.

### 4. 📄 Konten Halaman Dinamis
Pengaturan konten seluruh halaman website (Hero Banner, Tentang Kami, Layanan, Karir) dapat diubah secara *real-time* melalui endpoint admin tanpa perlu deploy ulang atau menyentuh kode.

### 5. 🛡️ Proteksi API Lintas-Modul
`AuthInterceptor` dan `GlobalExceptionHandler` memastikan semua request terautentikasi dengan benar dan setiap error dikembalikan dalam format JSON yang konsisten dan informatif.

---

## 🐛 Troubleshooting Umum

| Error | Kemungkinan Penyebab | Solusi |
|---|---|---|
| `Connection refused` pada port 5432 | PostgreSQL tidak berjalan | Jalankan service PostgreSQL di komputer Anda |
| `password authentication failed` | Kredensial database salah | Periksa `username` dan `password` di `application.yaml` |
| `database "db_mitradayakreasi" does not exist` | Database belum dibuat | Jalankan `CREATE DATABASE db_mitradayakreasi;` di psql |
| Error 500 saat upload gambar | Data base64 terlalu panjang | Pastikan kolom `image_url` bertipe `TEXT` di database |
| Error 400 pada endpoint `/api/career/settings` | Format JSON payload tidak sesuai | Pastikan `potentials` dan `cultures` dikirim sebagai JSON Array `[...]` |
| Port 8080 sudah digunakan | Aplikasi lain berjalan di port tersebut | Tambahkan `server.port: 8081` di `application.yaml` |

---

## 👥 Kontributor

| Nama | Peran |
|---|---|
| [Nama Anda] | Backend Developer |
| [Nama Lain] | [Peran] |

---

## 📄 Lisensi

Proyek ini dilisensikan di bawah **MIT License** — lihat file [LICENSE](LICENSE) untuk detail lebih lanjut.

---

<div align="center">

Dibuat dengan ❤️ oleh Tim **Mitra Daya Kreasi (MDK)**

</div>
