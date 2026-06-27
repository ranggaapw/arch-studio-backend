package com.mitradayakreasi.backend.modules.user;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;

@Component
public class UserSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final EntityManager entityManager;

    public UserSeeder(UserRepository userRepository, EntityManager entityManager){
        this.userRepository = userRepository;
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Migrasi kolom cv_file_data ke TEXT secara aman jika sebelumnya dibuat sebagai VARCHAR
        try {
            entityManager.createNativeQuery("ALTER TABLE job_applications ALTER COLUMN cv_file_data TYPE TEXT").executeUpdate();
            System.out.println("✅ Kolom cv_file_data berhasil dimigrasikan ke tipe TEXT!");
        } catch (Exception e) {
            System.out.println("ℹ️ Informasi: Gagal migrasi kolom cv_file_data (mungkin tabel belum dibuat atau kolom sudah bertipe TEXT): " + e.getMessage());
        }

        // Cek apakah database masih kosong
        if (userRepository.count() == 0){
            User admin = new User();
            admin.setUsername("admin");
            admin.setNamaLengkap("super admin");

            // Password 'admin123' diacak menggunakan BCrypt sebelum disimpan
            admin.setPassword(BCrypt.hashpw("admin123", BCrypt.gensalt()));

            userRepository.save(admin);
            System.out.println("✅ Akun Admin berhasil dibuat! Username: admin | Password: admin123");
        }
    }
}
