package com.mitradayakreasi.backend.modules.serviceitem;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ServiceItemService {

    private final ServiceItemRepository serviceItemRepository;

    public ServiceItemService(ServiceItemRepository serviceItemRepository) {
        this.serviceItemRepository = serviceItemRepository;
    }

    public List<ServiceItem> getAll() {
        return serviceItemRepository.findAll();
    }

    public ServiceItem getById(Long id) {
        return serviceItemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service tidak ditemukan"));
    }

    public ServiceItem create(ServiceItem serviceItem) {
        return serviceItemRepository.save(serviceItem);
    }

    public ServiceItem update(Long id, ServiceItem request) {
        ServiceItem serviceItem = getById(id);
        serviceItem.setTitle(request.getTitle());
        serviceItem.setDescription(request.getDescription());
        serviceItem.setIcon(request.getIcon());
        return serviceItemRepository.save(serviceItem);
    }

    public void delete(Long id) {
        ServiceItem serviceItem = getById(id);
        serviceItemRepository.delete(serviceItem);
    }
}
