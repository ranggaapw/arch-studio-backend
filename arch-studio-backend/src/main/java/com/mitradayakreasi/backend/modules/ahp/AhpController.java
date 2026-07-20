package com.mitradayakreasi.backend.modules.ahp;

import com.mitradayakreasi.backend.model.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ahp")
public class AhpController {

    private final AhpService ahpService;

    public AhpController(AhpService ahpService) {
        this.ahpService = ahpService;
    }

    @PostMapping("/calculate")
    public ApiResponse<AhpResult> calculate(@RequestBody AhpRequest request) {
        AhpResult result = ahpService.calculate(request);
        return new ApiResponse<>(result, "Kalkulasi AHP berhasil diselesaikan", 200);
    }
}
