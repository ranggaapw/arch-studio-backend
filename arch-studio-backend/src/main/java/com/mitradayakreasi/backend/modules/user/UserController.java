package com.mitradayakreasi.backend.modules.user;

import com.mitradayakreasi.backend.model.WebResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public WebResponse<TokenResponse> login(@RequestBody LoginRequest request){
        // serahkan data login ke otak (service) lalu bungkus hasilnya dengan WebResponse
        TokenResponse tokenResponse = userService.login(request);
        return new WebResponse<>(200, "OK", tokenResponse);
    }
}
