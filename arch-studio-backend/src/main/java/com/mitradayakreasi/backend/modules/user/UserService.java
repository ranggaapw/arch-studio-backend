package com.mitradayakreasi.backend.modules.user;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, JwtService jwtService){
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public TokenResponse login(LoginRequest request){
        // 1. cari admin di database berdasarkan username
        User user = userRepository.findById(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username atau Password salah"));

        // 2. cocokkan password ketikan dengan password acak di database
        if (BCrypt.checkpw(request.getPassword() , user.getPassword())){
            // 3. jika cocok, jalankan mesin pencetak gelap VIP (token)
            String token = jwtService.generateToken(user.getUsername());
            return new TokenResponse(token);
        } else {
            // jika password salah, tolak mentah mentah
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username atau Password salah");
        }
    }
}
