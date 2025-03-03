package com.hirehub.service;

import com.hirehub.dao.UsersDAO;
import com.hirehub.dao.UsersDAOIMPL;
import com.hirehub.model.Users;
import java.util.List;

public class UserService {
    private UsersDAO usersDAO;

    public UserService() {
        this.usersDAO = new UsersDAOIMPL();
    }

    /**
     * Creates a new user in the database
     * 
     * @param users The user object to create
     * @return true if the creation was successful, false otherwise
     */
    public boolean createUser(Users users) {
        try {
            usersDAO.add(users);
            return users.getuserId() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates an existing user in the database
     * 
     * @param users The user object with updated values
     * @return true if the update was successful, false otherwise
     */
    public boolean updateUser(Users users) {
        try {
            usersDAO.update(users);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a user from the database
     * 
     * @param userId The ID of the user to delete
     * @return true if the deletion was successful, false otherwise
     */
    public boolean deleteUser(int userId) {
        try {
            usersDAO.delete(userId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves a user by their ID
     * 
     * @param userId The ID of the user to retrieve
     * @return The user object if found, null otherwise
     */
    public Users getUserById(int userId) {
        return usersDAO.getuserId(userId);
    }

    /**
     * Retrieves all users from the database
     * 
     * @return A list of all users
     */
    public List<Users> getAllUsers() {
        return usersDAO.getAll();
    }
}
