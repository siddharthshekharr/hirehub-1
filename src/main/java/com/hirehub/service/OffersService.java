package com.hirehub.service;

import com.hirehub.dao.OffersDAO;
import com.hirehub.dao.OffersDAOImpl;
import com.hirehub.model.Offers;
import java.util.List;

public class OffersService {
    private OffersDAO offersDAO;

    public OffersService() {
        this.offersDAO = new OffersDAOImpl();
    }

    public void createOffers() {
        offersDAO.add(null);
    }

    public void updateOffers() {
        offersDAO.update(null);
    }

    public void deleteOffers(int id) {
        Offers offer = offersDAO.getByID(id);
        if (offer != null) {
            offersDAO.delete(offer);
        }
    }

    public void getOfferById(int id) {
        offersDAO.getByID(id);
    }

    public List<Offers> getAllOffers() {
        return offersDAO.getAll();
    }

}
