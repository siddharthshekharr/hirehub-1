package com.hirehub.service;

import com.hirehub.dao.InterviewsDAO;
import com.hirehub.dao.InterviewsDAOImpl;
import com.hirehub.model.Interviews;
import com.hirehub.model.Enums.interviewStatus;
import java.util.Date;
import java.util.List;

public class InterviewsService {
    private InterviewsDAO interviewsDAO;

    public InterviewsService() {
        this.interviewsDAO = new InterviewsDAOImpl();
    }

    /**
     * Creates a new interview in the database
     * 
     * @param applicationID The ID of the application this interview is for
     * @param interviewDate The date and time of the interview
     * @param feedback      Optional feedback for the interview
     * @param status        The status of the interview (SCHEDULED, COMPLETED,
     *                      CANCELLED, NO_SHOW)
     * @return The created interview object with its ID set
     */
    public Interviews createInterview(int applicationID, Date interviewDate, String feedback, interviewStatus status) {
        Interviews interview = new Interviews();
        interview.setapplicationID(applicationID);
        interview.setinterviewDate(interviewDate);
        interview.setfeedback(feedback);
        interview.setstatus(status);

        interviewsDAO.add(interview);
        return interview;
    }

    /**
     * Updates an existing interview in the database
     * 
     * @param interview The interview object with updated values
     * @return true if the update was successful, false otherwise
     */
    public boolean updateInterview(Interviews interview) {
        try {
            interviewsDAO.update(interview);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes an interview from the database
     * 
     * @param id The ID of the interview to delete
     * @return true if the deletion was successful, false otherwise
     */
    public boolean deleteInterview(int id) {
        try {
            interviewsDAO.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves an interview by its ID
     * 
     * @param id The ID of the interview to retrieve
     * @return The interview object if found, null otherwise
     */
    public Interviews getInterviewById(int id) {
        return interviewsDAO.getByID(id);
    }

    /**
     * Retrieves all interviews from the database
     * 
     * @return A list of all interviews
     */
    public List<Interviews> getAllInterviews() {
        return interviewsDAO.getAll();
    }
}