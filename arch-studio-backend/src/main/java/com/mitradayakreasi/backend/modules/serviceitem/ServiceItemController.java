package com.mitradayakreasi.backend.modules.serviceitem;

import com.mitradayakreasi.backend.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceItemController {

    private final ServiceItemService serviceItemService;

    public ServiceItemController(ServiceItemService serviceItemService) {
        this.serviceItemService = serviceItemService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ServiceItem>>> getAll() {
        List<ServiceItem> services = serviceItemService.getAll();
        ApiResponse<List<ServiceItem>> response = new ApiResponse<>(services, "Berhasil mengambil seluruh daftar layanan", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ServiceItem>> create(@RequestBody ServiceItem serviceItem) {
        ServiceItem created = serviceItemService.create(serviceItem);
        ApiResponse<ServiceItem> response = new ApiResponse<>(created, "Layanan baru berhasil ditambahkan", HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ServiceItem>> update(@PathVariable Long id, @RequestBody ServiceItem request) {
        ServiceItem updated = serviceItemService.update(id, request);
        ApiResponse<ServiceItem> response = new ApiResponse<>(updated, "Layanan berhasil diupdate", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        serviceItemService.delete(id);
        ApiResponse<Void> response = new ApiResponse<>(null, "Layanan berhasil dihapus", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }
}
