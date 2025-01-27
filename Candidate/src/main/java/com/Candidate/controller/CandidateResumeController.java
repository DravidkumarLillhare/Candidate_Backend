package com.Candidate.controller;

import com.Candidate.Service.CandidateResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CandidateResumeController {

    @Autowired
    private CandidateResumeService candidateResumeService;

    @PostMapping("/resumeUpload/{candidateId}")
    public ResponseEntity<String> uploadResume(
            @PathVariable int candidateId,
            @RequestParam("file") MultipartFile file) throws IOException {
        String filename = candidateResumeService.uploadResume(file, candidateId);
        return ResponseEntity.ok("Resume uploaded successfully! Filename: " + filename);
    }

    @GetMapping("/resumeGet/{candidateId}") 
    public ResponseEntity<FileSystemResource> getResumeByCandidateId(@PathVariable int candidateId) {
        return candidateResumeService.getResumeByCandidateId(candidateId)
                .map(resumeOptional -> {
                    File file = new File(resumeOptional.getResumeFilePath());
                    FileSystemResource resource = new FileSystemResource(file);

                    return ResponseEntity.ok()
                            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
                            .body(resource);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/resumeDelete/{candidateId}")
    public ResponseEntity<String> deleteResumeByCandidateId(@PathVariable int candidateId) {
        try {
            candidateResumeService.deleteResumeByCandidateId(candidateId);
            return ResponseEntity.ok("Resume deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting resume: " + e.getMessage());
        }
    }
}