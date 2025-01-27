package com.Candidate.repository;

import com.Candidate.entity.CandidateResume;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CandidateResumeRepository extends JpaRepository<CandidateResume, Integer> {

    @Query("SELECT r FROM CandidateResume r WHERE r.candidate.id = :candidateId")
    Optional<CandidateResume> findByCandidateId(@Param("candidateId") int candidateId);


    @Modifying
    @Transactional
    @Query("DELETE FROM CandidateResume r WHERE r.candidate.id = :candidateId")
    void deleteByCandidateId(@Param("candidateId") int candidateId);
}
