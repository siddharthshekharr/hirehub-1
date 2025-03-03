// model represents data structure, contains attributes and methods
package com.hirehub.model;

import java.util.Date;

import com.hirehub.model.Enums.interviewStatus;

public class Interviews {

    private int interviewID;
    private int applicationID;
    private Date interviewDate;
    private String feedback;
    private interviewStatus status;

    // constructor

    public Interviews() {

    }

    public Interviews(int interviewID, int applicationID, Date interviewDate, String feedback, String statusStr) {
        this.interviewID = interviewID;
        this.applicationID = applicationID;
        this.interviewDate = interviewDate;
        this.feedback = feedback;

        // Convert string to enum
        try {
            this.status = interviewStatus.valueOf(statusStr);
        } catch (IllegalArgumentException e) {
            this.status = interviewStatus.SCHEDULED; // Default value
        }
    }

    // Additional constructor that takes the enum directly
    public Interviews(int interviewID, int applicationID, Date interviewDate, String feedback, interviewStatus status) {
        this.interviewID = interviewID;
        this.applicationID = applicationID;
        this.interviewDate = interviewDate;
        this.feedback = feedback;
        this.status = status;
    }

    // getters and setters

    public int getinterviewID() {
        return interviewID;
    }

    public void setinterviewID(int interviewID) {
        this.interviewID = interviewID;
    }

    public int getapplicationID() {
        return applicationID;
    }

    public void setapplicationID(int applicationID) {
        this.applicationID = applicationID;
    }

    public Date getinterviewDate() {
        return interviewDate;
    }

    public void setinterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;

    }

    public String getfeedback() {
        return feedback;
    }

    public void setfeedback(String feedback) {
        this.feedback = feedback;
    }

    public interviewStatus getstatus() {
        return status;
    }

    public void setstatus(interviewStatus status) {
        this.status = status;
    }

    // Additional method to set status from a string
    public void setstatus(String statusStr) {
        try {
            this.status = interviewStatus.valueOf(statusStr);
        } catch (IllegalArgumentException e) {
            this.status = interviewStatus.SCHEDULED; // Default value
        }
    }

}