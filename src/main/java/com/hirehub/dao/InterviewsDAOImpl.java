package com.hirehub.dao;

import com.hirehub.model.Enums;
import com.hirehub.model.Interviews;
import com.hirehub.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InterviewsDAOImpl implements InterviewsDAO {

    private Connection connection;

    public InterviewsDAOImpl() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void add(Interviews interview) {
        String sql = "INSERT INTO interviews (application_id, interview_date, feedback, status) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, interview.getapplicationID());
            pstmt.setTimestamp(2, new Timestamp(interview.getinterviewDate().getTime())); // Convert Date to Timestamp
                                                                                          // for SQL
            pstmt.setString(3, interview.getfeedback());
            pstmt.setString(4, interview.getstatus().name()); // Convert enum to string

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    interview.setinterviewID(generatedKeys.getInt(1)); // Set the generated ID
                } else {
                    throw new SQLException("Creating interview failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Interviews interview) {
        String sql = "UPDATE interviews SET application_id = ?, interview_date = ?, feedback = ?, status = ? WHERE interview_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, interview.getapplicationID());
            pstmt.setTimestamp(2, new Timestamp(interview.getinterviewDate().getTime())); // Convert Date to Timestamp
                                                                                          // for SQL
            pstmt.setString(3, interview.getfeedback());
            pstmt.setString(4, interview.getstatus().name()); // Convert enum to string
            pstmt.setInt(5, interview.getinterviewID()); // Using interview_id for the WHERE clause

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int interviewID) {
        String sql = "DELETE FROM interviews WHERE interview_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, interviewID);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Interviews getByID(int interviewID) {
        String sql = "SELECT * FROM interviews WHERE interview_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, interviewID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractInterviewFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Interviews> getAll() {
        List<Interviews> interviewsList = new ArrayList<>();
        String sql = "SELECT * FROM interviews";

        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                interviewsList.add(extractInterviewFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return interviewsList;
    }

    @Override
    public List<Interviews> getByApplicationID(int applicationID) {
        List<Interviews> interviewsList = new ArrayList<>();
        String sql = "SELECT * FROM interviews WHERE application_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, applicationID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                interviewsList.add(extractInterviewFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return interviewsList;
    }

    // Helper method to convert a ResultSet row into an Interviews object
    private Interviews extractInterviewFromResultSet(ResultSet rs) throws SQLException {
        Interviews interview = new Interviews();

        interview.setinterviewID(rs.getInt("interview_id"));
        interview.setapplicationID(rs.getInt("application_id"));
        interview.setinterviewDate(rs.getTimestamp("interview_date"));
        interview.setfeedback(rs.getString("feedback"));

        // Convert string to enum
        String statusStr = rs.getString("status");
        try {
            interview.setstatus(Enums.interviewStatus.valueOf(statusStr));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid interview status: " + statusStr);
            interview.setstatus(Enums.interviewStatus.SCHEDULED); // Default value
        }

        return interview;
    }
}
