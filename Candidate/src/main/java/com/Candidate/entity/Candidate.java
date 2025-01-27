package com.Candidate.entity;

import jakarta.persistence.*;

@Entity
@Table(name="CandidateData")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="FirstName")
    private String firstName;

    @Column(name="LastName")
    private String lastName;

    @Column(name="Location")
    private String location;

    @Column(name="Experience")
    private int experience;

    @Column(name="Qualification")
    private String qualification;

    @Column(name="Email")
    private String email;

//    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<CandidateResume> candidateResumes;
//
//    public List<CandidateResume> getCandidateResumes() {
//        return candidateResumes;
//    }
//
//    public void setCandidateResumes(List<CandidateResume> candidateResumes) {
//        this.candidateResumes = candidateResumes;
//    }
    public Candidate() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
