package com.mitradayakreasi.backend.modules.serviceitem;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class WorkProcessService {

    private final WorkProcessRepository workProcessRepository;

    public WorkProcessService(WorkProcessRepository workProcessRepository) {
        this.workProcessRepository = workProcessRepository;
    }

    public List<WorkProcess> getAll() {
        return workProcessRepository.findAll();
    }

    public WorkProcess getById(Long id) {
        return workProcessRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Work process tidak ditemukan"));
    }

    public WorkProcess create(WorkProcess workProcess) {
        return workProcessRepository.save(workProcess);
    }

    public WorkProcess update(Long id, WorkProcess request) {
        WorkProcess workProcess = getById(id);
        workProcess.setTitle(request.getTitle());
        workProcess.setDescription(request.getDescription());
        workProcess.setIconName(request.getIconName());
        return workProcessRepository.save(workProcess);
    }

    public void delete(Long id) {
        WorkProcess workProcess = getById(id);
        workProcessRepository.delete(workProcess);
    }
}
