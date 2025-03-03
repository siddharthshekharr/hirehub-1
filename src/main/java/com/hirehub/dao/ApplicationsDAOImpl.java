//implements DAO interface containing actual logic to interact with the DB
package com.hirehub.dao;

import com.hirehub.model.Applications;
import com.hirehub.model.Enums;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;
import com.hirehub.util.DatabaseConnection;
import com.mysql.cj.protocol.Resultset;

public class ApplicationsDAOImpl implements ApplicationsDAO {
    private Connection connection; // create instance of connection

    public ApplicationsDAOImpl() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void add(Applications applications) {
        String sql = "INSERT INTO applications (job_id, candidate_id, application_date, status, current_salary, expected_salary, notice_period, cover_letter) "
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, applications.getjobID());
            pstmt.setInt(2, applications.getcandidateID());
            pstmt.setTimestamp(3, new Timestamp(applications.getapplicationDate().getTime())); // convert Date to
                                                                                               // Timestamp for sql
            pstmt.setString(4, applications.getStatus().name()); // Convert enum to string

            // Handle null values properly
            if (applications.getCurrentSalary() != null) {
                pstmt.setBigDecimal(5, applications.getCurrentSalary());
            } else {
                pstmt.setNull(5, java.sql.Types.DECIMAL);
            }

            if (applications.getExpectedSalary() != null) {
                pstmt.setBigDecimal(6, applications.getExpectedSalary());
            } else {
                pstmt.setNull(6, java.sql.Types.DECIMAL);
            }

            if (applications.getnoticePeriod() != null) {
                pstmt.setInt(7, applications.getnoticePeriod());
            } else {
                pstmt.setNull(7, java.sql.Types.INTEGER);
            }

            if (applications.getcoverLetter() != null) {
                pstmt.setString(8, applications.getcoverLetter());
            } else {
                pstmt.setNull(8, java.sql.Types.VARCHAR);
            }

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    applications.setapplicationID(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating application failed, no ID obtained");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding application: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(int applicationID) {
        String sql = "DELETE FROM applications WHERE application_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, applicationID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Applications applications) {
        String sql = "UPDATE applications SET job_id = ?, candidate_id = ?, application_date = ?, status = ?, current_salary = ?, expected_salary = ?, notice_period = ?, cover_letter = ? "
                +
                "WHERE application_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, applications.getjobID());
            pstmt.setInt(2, applications.getcandidateID());
            pstmt.setTimestamp(3, new Timestamp(applications.getapplicationDate().getTime())); // convert Date to
                                                                                               // Timestamp for sql
            pstmt.setString(4, applications.getStatus().name()); // convert enum to string

            // Handle null values properly
            if (applications.getCurrentSalary() != null) {
                pstmt.setBigDecimal(5, applications.getCurrentSalary());
            } else {
                pstmt.setNull(5, java.sql.Types.DECIMAL);
            }

            if (applications.getExpectedSalary() != null) {
                pstmt.setBigDecimal(6, applications.getExpectedSalary());
            } else {
                pstmt.setNull(6, java.sql.Types.DECIMAL);
            }

            if (applications.getnoticePeriod() != null) {
                pstmt.setInt(7, applications.getnoticePeriod());
            } else {
                pstmt.setNull(7, java.sql.Types.INTEGER);
            }

            if (applications.getcoverLetter() != null) {
                pstmt.setString(8, applications.getcoverLetter());
            } else {
                pstmt.setNull(8, java.sql.Types.VARCHAR);
            }

            pstmt.setInt(9, applications.getapplicationID());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating application: " + e.getMessage(), e);
        }
    }

    @Override
    public Applications getByID(int applicationID) {
        String sql = "SELECT * FROM applications WHERE application_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, applicationID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractApplicationFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<Applications> getAll() {
        List<Applications> applicationsList = new ArrayList<>();
        String sql = "SELECT * FROM applications";

        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                applicationsList.add(extractApplicationFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return applicationsList;
    }

    private Applications extractApplicationFromResultSet(ResultSet rs) throws SQLException {
        Applications application = new Applications();

        application.setapplicationID(rs.getInt("application_id"));
        application.setjobID(rs.getInt("job_id"));
        application.setcandidateID(rs.getInt("candidate_id"));
        application.setapplicationDate(rs.getTimestamp("application_date"));

        // Convert string to enum
        String statusStr = rs.getString("status");
        try {
            application.setstatus(statusStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid application status: " + statusStr);
            application.setstatus(Enums.applicationStatus.APPLIED); // Default value
        }

        // Get optional fields
        try {
            application.setCurrentSalary(rs.getBigDecimal("current_salary"));
        } catch (SQLException e) {
            // Field might be null, ignore
        }

        try {
            application.setExpectedSalary(rs.getBigDecimal("expected_salary"));
        } catch (SQLException e) {
            // Field might be null, ignore
        }

        try {
            application.setNoticePeriod(rs.getInt("notice_period"));
        } catch (SQLException e) {
            // Field might be null, ignore
        }

        try {
            application.setcoverLetter(rs.getString("cover_letter"));
        } catch (SQLException e) {
            // Field might be null, ignore
        }

        return application;
    }
}
