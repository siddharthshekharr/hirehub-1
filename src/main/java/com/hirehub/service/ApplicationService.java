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

    public void createApplication(Applications applications) {
        applicationsDAO.add(applications);
    }

    public void updateApplication(Applications applications) {
        applicationsDAO.update(applications);
    }

    public void deleteApplication(int id) {
        applicationsDAO.delete(id);
    }

    public Applications getApplicationById(int id) {
        return applicationsDAO.getByID(id);
    }

    public List<Applications> getAllApplications() {
        return applicationsDAO.getAll();
    }
}
