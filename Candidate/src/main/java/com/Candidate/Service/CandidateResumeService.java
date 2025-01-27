package com.Candidate.Service;

import com.Candidate.entity.Candidate;
import com.Candidate.entity.CandidateResume;
import com.Candidate.repository.CandidateRepository;
import com.Candidate.repository.CandidateResumeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class CandidateResumeService {

    @Value("${resume.upload.dir}")
    private String uploadDir;

    private final CandidateResumeRepository candidateResumeRepository;
    private final CandidateRepository candidateRepository;

    public CandidateResumeService(
            CandidateResumeRepository candidateResumeRepository,
            CandidateRepository candidateRepository) {
        this.candidateResumeRepository = candidateResumeRepository;
        this.candidateRepository = candidateRepository;
    }

    public String uploadResume(MultipartFile file, int candidateId) throws IOException {

        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }


        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uniqueFilename = UUID.randomUUID().toString() + fileExtension;
        String filePath = uploadDir + File.separator + uniqueFilename;

        File destinationFile = new File(filePath);
        file.transferTo(destinationFile);

        candidateResumeRepository.findByCandidateId(candidateId)
                .ifPresent(existingResume -> candidateResumeRepository.delete(existingResume));


        CandidateResume resume = new CandidateResume();
        resume.setResumeFilePath(filePath);
        resume.setCandidate(candidate);
        candidateResumeRepository.save(resume);

        return uniqueFilename;
    }

    public Optional<CandidateResume> getResumeByCandidateId(int candidateId) {
        return candidateResumeRepository.findByCandidateId(candidateId);
    }

    public void deleteResumeByCandidateId(int candidateId) {
        candidateResumeRepository.findByCandidateId(candidateId)
                .ifPresent(resume -> {

                    new File(resume.getResumeFilePath()).delete();

                    candidateResumeRepository.delete(resume);
                });
    }
}