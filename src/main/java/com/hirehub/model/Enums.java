package com.hirehub.model;

public class Enums {

    public enum jobStatus {
        DRAFT, OPEN, CLOSED, ON_HOLD, UNKNOWN
    }

    public enum applicationStatus {
        APPLIED, SCREENING, SHORTLISTED, INTERVIEWING, OFFERED, HIRED, REJECTED
    }

    public enum interviewStatus {
        SCHEDULED, COMPLETED, CANCELLED, NO_SHOW
    }

    public enum UserRole {
        ADMIN, RECRUITER, HIRING_MANAGER, INTERVIEWER
    }

    // Added for backward compatibility with existing code
    public enum candidatesStatus {
        ACTIVE, INACTIVE, BLACKLISTED, UNKNOWN
    }
}
