package com.hirehub.model;

import java.util.Date;
import java.math.BigDecimal;

public class Offers {

    private int offerID;
    private int applicationID;
    private BigDecimal salary;
    private Date startDate;
    private String status;
    // constructor

    public Offers() {

    }

    public Offers(int offerID, int applicationID, BigDecimal salary, Date startDate, String status) {
        this.offerID = offerID;
        this.applicationID = applicationID;
        this.salary = salary;
        this.startDate = startDate;
        this.status = status;
    }

    // getters and setters

    public int getofferID() {
        return offerID;
    }

    public void setofferID(int offerID) {
        this.offerID = offerID;
    }

    public int getapplicationID() {
        return applicationID;
    }

    public void setapplicationID(int applicationID) {
        this.applicationID = applicationID;
    }

    public BigDecimal getsalary() {
        return salary;
    }

    public void setsalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getstartDate() {
        return startDate;
    }

    public void setstartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }

}