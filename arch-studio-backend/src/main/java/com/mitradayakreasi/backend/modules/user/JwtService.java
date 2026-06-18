package com.mitradayakreasi.backend.modules.user;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {
    
    // kunci rahasia untuk gembok JWT (minimal harus 32 karakter)
    private static final String SECRET_KEY = "MitraDayaKreasiSangatRahasiaSekali2026!";

    // fungsi untuk mengubah teks rahasia diatas menjadi kriptografi
    private SecretKey getSigningKey (){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    // fungsi untuk mencetak token
    public String generateToken(String username){
        return Jwts.builder()
                .subject(username) // milik siapa?
                .issuedAt(new Date()) // dibuat kapan?
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // berlaku 1 hari (dalam milidetik)
                .signWith(getSigningKey()) //stempel dengan kunci rahasia
                .compact();
    }

    // Fungsi untuk membongkar, memvalidasi, dan mengambil username dari token
    public String validateTokenAndGetUsername(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject(); // Mengembalikan username pemilik token
        } catch (Exception e) {
            // Jika token palsu, dimodifikasi, atau kedaluwarsa, langsung lempar error
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.UNAUTHORIZED, "Token tidak valid atau telah kedaluwarsa"
            );
        }
    }

}
