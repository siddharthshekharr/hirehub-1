package com.hirehub.service;

import com.hirehub.dao.ApplicationsDAO;
import com.hirehub.dao.ApplicationsDAOImpl;
import com.hirehub.model.Applications;
import java.util.List;

public class ApplicationService {
    private ApplicationsDAO applicationsDAO;

    public ApplicationService() {
        this.applicationsDAO = new ApplicationsDAOImpl();
    }

    public boolean createApplication(Applications applications) {
        try {
            applicationsDAO.add(applications);
            return applications.getapplicationID() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateApplication(Applications applications) {
        try {
            applicationsDAO.update(applications);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteApplication(int id) {
        try {
            applicationsDAO.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Applications getApplicationById(int id) {
        return applicationsDAO.getByID(id);
    }

    public List<Applications> getAllApplications() {
        return applicationsDAO.getAll();
    }
}
