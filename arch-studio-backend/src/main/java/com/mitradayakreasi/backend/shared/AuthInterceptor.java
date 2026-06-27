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
        String path = request.getRequestURI();
        String method = request.getMethod();

        // 1. Skenario Bebas API Project: Boleh GET (Lihat portofolio)
        if (path.startsWith("/api/projects") && "GET".equalsIgnoreCase(method)) {
            return true;
        }

        // 2. Skenario Bebas API Contact: Boleh POST (Kirim pesan dari pengunjung)
        if (path.startsWith("/api/contacts") && "POST".equalsIgnoreCase(method)) {
            return true;
        }

        // 3. Skenario Bebas API Karir & Lowongan
        if (path.startsWith("/api/jobs") && "GET".equalsIgnoreCase(method)) {
            return true;
        }
        if (path.startsWith("/api/applications") && "POST".equalsIgnoreCase(method)) {
            return true;
        }
        if (path.startsWith("/api/career/settings") && "GET".equalsIgnoreCase(method)) {
            return true;
        }

        // 3. Sisanya (POST/PUT/DELETE Project, dan GET Contact) WAJIB pakai Token!
        String authHeader = request.getHeader("Authorization");
        
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Akses ditolak: Token tidak ditemukan");
        }

        String token = authHeader.substring(7);
        String username = jwtService.validateTokenAndGetUsername(token);
        request.setAttribute("adminUsername", username);

        return true; 
    }
}