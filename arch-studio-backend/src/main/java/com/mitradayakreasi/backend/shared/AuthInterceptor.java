package com.mitradayakreasi.backend.shared;

import com.mitradayakreasi.backend.modules.user.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtService jwtService;

    public AuthInterceptor(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. Skenario Bebas: Jika React hanya melakukan GET (melihat data), loloskan langsung!
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        // 2. Skenario Ketat: Jika POST, PUT, DELETE, periksa header "Authorization"
        String authHeader = request.getHeader("Authorization");
        
        // Periksa apakah formatnya benar: "Bearer teks_token_panjang"
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Akses ditolak: Token tidak ditemukan");
        }

        // Potong kata "Bearer " (7 karakter) untuk mengambil token murninya saja
        String token = authHeader.substring(7);
        
        // Validasi token ke JwtService
        String username = jwtService.validateTokenAndGetUsername(token);
        
        // Simpan username di atribut request agar bisa dilacak jika dibutuhkan nanti
        request.setAttribute("adminUsername", username);

        return true; // Token sah, silakan masuk ke Controller!
    }
}