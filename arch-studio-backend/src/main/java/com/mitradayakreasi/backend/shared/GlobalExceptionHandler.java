package com.mitradayakreasi.backend.shared;

import com.mitradayakreasi.backend.model.WebResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice // Menjadikan kelas ini penjaga seluruh Controller
public class GlobalExceptionHandler {

    // Menangkap error khusus dari ResponseStatusException (misal: password salah)
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<WebResponse<String>> handleResponseStatusException(ResponseStatusException ex) {
        
        // Membungkus pesan error ke dalam WebResponse universal kita
        WebResponse<String> response = new WebResponse<>(
                ex.getStatusCode().value(), // Mengambil angka status (misal: 401)
                "ERROR",
                ex.getReason() // Mengambil pesan "Username atau password salah"
        );
        
        return new ResponseEntity<>(response, ex.getStatusCode());
    }
}