package com.hirehub.model;

import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
//import java.util.Objects;
import com.hirehub.model.Enums.applicationStatus;

public class Applications implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer jobID;
    private Integer candidateID;
    private Date applicationDate;
    private BigDecimal currentSalary;
    private BigDecimal expectedSalary;
    private Integer noticePeriod;
    private Date createdAt;
    private Date updatedAt;
    private String applicationNotes;
    private Integer lastUpdatedBy;
    private applicationStatus status;
    private String coverLetter;
    private int applicationID;

    // Default Constructors

    public Applications() {

    }

    // Constructor with required fields

    public Applications(int jobID, int candidateID, Date applicationDate, String status, int applicationID) {
        this(); // Calling the default constructor to initialize default values for optional
                // fields
        setjobID(jobID);
        setcandidateID(candidateID);
        setapplicationID(applicationID);
        setapplicationDate(applicationDate != null ? applicationDate : new Date()); // Use current date if null

        // Set status based on the string value
        if (status != null) {
            try {
                this.status = applicationStatus.valueOf(status);
            } catch (IllegalArgumentException e) {
                this.status = applicationStatus.APPLIED; // Default to APPLIED if invalid status
            }
        } else {
            this.status = applicationStatus.APPLIED; // Default to APPLIED if status is null
        }
    }

    // Getters and setters with validation

    public int getjobID() {
        return jobID;
    }

    public void setjobID(Integer jobID) {
        if (jobID == null || jobID <= 0) {
            throw new IllegalArgumentException("Invalid job ID");
        }
        this.jobID = jobID;
    }

    public int getcandidateID() {
        return candidateID;
    }

    public void setcandidateID(Integer candidateID) {
        if (candidateID == null || candidateID <= 0) {
            throw new IllegalArgumentException("Invalid candidate ID");
        }
        this.candidateID = candidateID;
    }

    public int getapplicationID() {
        return applicationID;
    }

    public void setapplicationID(int applicationID) {
        this.applicationID = applicationID;

    }

    public Date getapplicationDate() {
        return applicationDate != null ? new Date(applicationDate.getTime()) : null;
    }

    public void setapplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate != null ? new Date(applicationDate.getTime()) : null;
    }

    public applicationStatus getStatus() {
        return status;
    }

    public void setstatus(String status) {
        try {
            this.status = applicationStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid application status: " + status);
        }
    }

    public void setstatus(applicationStatus status) {
        this.status = status;
    }

    public BigDecimal getCurrentSalary() {
        return currentSalary;
    }

    public void setCurrentSalary(BigDecimal currentSalary) {
        if (currentSalary != null && currentSalary.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Current salary cannot be negative");
        }
        this.currentSalary = currentSalary;
    }

    public BigDecimal getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(BigDecimal expectedSalary) {
        if (expectedSalary != null && expectedSalary.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Expected salary cannot be negative");
        }
        this.expectedSalary = expectedSalary;
    }

    public Integer getnoticePeriod() {
        return noticePeriod;
    }

    public void setNoticePeriod(Integer noticePeriod) {
        if (noticePeriod != null && noticePeriod < 0) {
            throw new IllegalArgumentException("Notice period cannot be negative");
        }
        this.noticePeriod = noticePeriod;
    }

    public String getcoverLetter() {
        return coverLetter;
    }

    public void setcoverLetter(String coverLetter) {
        this.coverLetter = coverLetter != null ? coverLetter.trim() : null;
    }

    public String getApplicationNotes() {
        return applicationNotes;
    }

    public void setApplicationNotes(String applicationNotes) {
        this.applicationNotes = applicationNotes != null ? applicationNotes.trim() : null;
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

    public Integer getLastUpdatedby() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
// @override
