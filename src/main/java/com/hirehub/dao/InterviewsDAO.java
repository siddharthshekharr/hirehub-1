package com.hirehub.dao;

//information for what operations can be performed on interviews table

import com.hirehub.model.Interviews;
import java.util.List;

//complete abstract class
public interface InterviewsDAO {

    void add(Interviews interviews);

    void update(Interviews interviews);

    void delete(int interviewID);

    Interviews getByID(int id);

    List<Interviews> getAll();

    // Added method to get interviews by application ID
    List<Interviews> getByApplicationID(int applicationID);

}
