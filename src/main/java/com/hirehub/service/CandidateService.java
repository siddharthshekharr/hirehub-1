//service layer acts as an intermediary between user input and requests (controller) and DAO layer(data persistance)
package com.hirehub.service;

import com.hirehub.dao.CandidatesDAO;
import com.hirehub.dao.CandidatesDAOIMPL;
import com.hirehub.model.Candidates;
import java.util.List;

public class CandidateService {
    private CandidatesDAO candidatesDAO;

    public CandidateService() {
        this.candidatesDAO = new CandidatesDAOIMPL();
    }

    public void createCandidate(Candidates candidates) {
        candidatesDAO.add(candidates);
    }

    public void updateCandidate(Candidates candidates) {
        candidatesDAO.update(candidates);
    }

    public void deleteCandidate(int id) {
        candidatesDAO.delete(id);
    }

    public Candidates getCandidateById(int id) {
        return candidatesDAO.getId(id);
    }

    public List<Candidates> getAllCandidates() {
        return candidatesDAO.getAll();
    }
}
