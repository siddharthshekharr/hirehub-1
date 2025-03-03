package com.hirehub.model;

import java.util.Date;
//import java.util.Objects;
import java.io.Serializable;;

// Constructors
public class Candidates implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String resumeURL;
    private Date registrationDate;
    private Date createdAt;
    private Date updatedAt;
    private CandidateStatus Status;

    public enum CandidateStatus {
        ACTIVE, INACTIVE, BLACKLISTED
    }

    // default constructor
    public Candidates() {
        this.registrationDate = new Date();
        this.createdAt = new Date();
        this.Status = CandidateStatus.ACTIVE;
    }

    // Constructor with all fields
    public Candidates(int id, String firstName, String lastName, String emailAddress, String phoneNumber,
            String resumeURL, CandidateStatus status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.resumeURL = resumeURL;
        this.Status = status;
        this.registrationDate = new Date();
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    // Constructor with required fields
    public Candidates(String firstName, String lastName, String emailAddress, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.registrationDate = new Date();
        this.createdAt = new Date();
        this.Status = CandidateStatus.ACTIVE;
    }

    // getters and setters with validation

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getfirstName() {
        return firstName;
    }

    public void setfirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        this.firstName = firstName.trim();
    }

    public String getlastName() {
        return lastName;
    }

    public void setlastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        this.lastName = lastName.trim();
    }

    public String getemailAddress() {
        return emailAddress;
    }

    public void setemailAddress(String emailAddress) {
        if (emailAddress == null || emailAddress.trim().isEmpty()) {
            throw new IllegalArgumentException("Email address cannot be empty");
        }
        this.emailAddress = emailAddress.toLowerCase().trim();
    }

    public String getphoneNumber() {
        return phoneNumber;
    }

    public void setphoneNumber(String phoneNumber2) {
        if (phoneNumber2 == null || phoneNumber2.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty");
        }
        this.phoneNumber = phoneNumber2.trim();
    }

    public String getresumeURL() {
        return resumeURL;

    }

    public void setresumeURL(String resumeURL) {
        this.resumeURL = resumeURL != null ? resumeURL.trim() : null;
    }

    public Date getregistrationDate() {
        return registrationDate;

    }

    public void setregistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate != null ? new Date(registrationDate.getTime()) : null;
    }

    public CandidateStatus getStatus() {
        return Status;
    }

    public void setStatus(CandidateStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        this.Status = status;
    }

    public Date getCreatedAt() {
        return createdAt != null ? new Date(createdAt.getTime()) : null;
    }

    protected void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt != null ? new Date(createdAt.getTime()) : null;

    }

    public Date getUpdatedAt() {
        return updatedAt != null ? new Date(updatedAt.getTime()) : null;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt != null ? new Date(updatedAt.getTime()) : null;
    }

}
