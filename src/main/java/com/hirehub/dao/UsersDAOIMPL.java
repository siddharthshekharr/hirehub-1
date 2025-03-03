package com.hirehub.dao;

import com.hirehub.model.Users;
import com.hirehub.model.Enums.UserRole;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;
import com.hirehub.util.DatabaseConnection;

public class UsersDAOIMPL implements UsersDAO {

    private Connection connection;

    public UsersDAOIMPL() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void add(Users users) {
        String sql = "INSERT INTO users (username, password_hash, email, first_name, last_name, role) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, users.getuserName());
            pstmt.setString(2, users.getpassword());
            pstmt.setString(3, users.getemail());
            pstmt.setString(4, users.getFirstName());
            pstmt.setString(5, users.getLastName());
            pstmt.setString(6, users.getrole());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                // if key was generated
                if (generatedKeys.next()) {
                    // sets the generated ID onto entity object, user object now has id populated
                    users.setuserId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding user: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Users users) {
        String sql = "UPDATE users SET username = ?, password_hash = ?, email = ?, first_name = ?, last_name = ?, role = ? WHERE user_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, users.getuserName());
            pstmt.setString(2, users.getpassword());
            pstmt.setString(3, users.getemail());
            pstmt.setString(4, users.getFirstName());
            pstmt.setString(5, users.getLastName());
            pstmt.setString(6, users.getrole());
            pstmt.setInt(7, users.getuserId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating user: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(int userId) {
        String sql = "DELETE FROM users WHERE user_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting user: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Users> getAll() {
        List<Users> users = new ArrayList<>();

        String sql = "SELECT * FROM users";

        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                users.add(extractUsersFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public Users getuserId(int id) {
        String sql = "SELECT * FROM users WHERE user_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractUsersFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Helper method used to Convert ResultSet row into a users object
    private Users extractUsersFromResultSet(ResultSet rs) throws SQLException {
        // Create a new users object
        Users users = new Users();

        // Populate the user object with values from the ResultSet
        users.setuserId(rs.getInt("user_id"));
        users.setuserName(rs.getString("username"));
        users.setpassword(rs.getString("password_hash"));
        users.setemail(rs.getString("email"));
        users.setFirstName(rs.getString("first_name"));
        users.setLastName(rs.getString("last_name"));
        users.setrole(rs.getString("role"));

        return users; // Return the populated user object
    }
}
