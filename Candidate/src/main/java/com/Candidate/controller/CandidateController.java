package com.Candidate.controller;

import com.Candidate.entity.Candidate;
import com.Candidate.Service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;
    @GetMapping("/")
    public String home() {
        return "Welcome to the Application!";
    }
    @GetMapping("/candidates")
    public List<Candidate> getAllCandidates() {
        return candidateService.getAllCandidates();
    }

    @PutMapping("/updateCandidate/{id}")
    public ResponseEntity<Candidate> updateCandidateById(@PathVariable int id, @RequestBody Candidate candidateDetails) {
        Candidate updatedCandidate = candidateService.updateCandidate(id, candidateDetails);
        return ResponseEntity.ok(updatedCandidate);
    }

    @GetMapping("/getCandidate/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable int id) {
        Candidate candidate = candidateService.getCandidateById(id);
        return ResponseEntity.ok(candidate);
    }

    @PostMapping("/addCandidate")
    public Candidate addCandidate(@RequestBody Candidate candidate) {
        return candidateService.addCandidate(candidate);
    }

    @DeleteMapping("/candidate/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCandidate(@PathVariable int id) {
        candidateService.deleteCandidate(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}