package com.mitradayakreasi.backend.modules.career;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class JobOpeningService {

    private final JobOpeningRepository jobOpeningRepository;

    public JobOpeningService(JobOpeningRepository jobOpeningRepository) {
        this.jobOpeningRepository = jobOpeningRepository;
    }

    public List<JobOpening> getAll() {
        return jobOpeningRepository.findAll();
    }

    public JobOpening getById(Long id) {
        return jobOpeningRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job opening tidak ditemukan"));
    }

    public JobOpening create(JobOpening jobOpening) {
        return jobOpeningRepository.save(jobOpening);
    }

    public JobOpening update(Long id, JobOpening request) {
        JobOpening jobOpening = getById(id);
        jobOpening.setTitle(request.getTitle());
        jobOpening.setType(request.getType());
        jobOpening.setLoc(request.getLoc());
        jobOpening.setDesc(request.getDesc());
        return jobOpeningRepository.save(jobOpening);
    }

    public void delete(Long id) {
        JobOpening jobOpening = getById(id);
        jobOpeningRepository.delete(jobOpening);
    }
}
