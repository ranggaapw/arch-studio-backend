package com.mitradayakreasi.backend.modules.user;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder implements CommandLineRunner {
    private final UserRepository userRepository;

    public UserSeeder(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
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
