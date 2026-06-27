package com.mitradayakreasi.backend.modules.career;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }

    public List<JobApplication> getAll() {
        return jobApplicationRepository.findAllByOrderByCreatedAtDesc();
    }

    public JobApplication getById(Long id) {
        return jobApplicationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lamaran pekerjaan tidak ditemukan"));
    }

    public JobApplication create(JobApplication jobApplication) {
        jobApplication.setIsRead(false);
        return jobApplicationRepository.save(jobApplication);
    }

    public JobApplication markAsRead(Long id) {
        JobApplication application = getById(id);
        application.setIsRead(true);
        return jobApplicationRepository.save(application);
    }

    public void delete(Long id) {
        JobApplication application = getById(id);
        jobApplicationRepository.delete(application);
    }
}
