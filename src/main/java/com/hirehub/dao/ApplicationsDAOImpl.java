//implements DAO interface containing actual logic to interact with the DB
package com.hirehub.dao;

import com.hirehub.model.Applications;
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
        String sql = "INSERT INTO applications (job_id, candidate_id, application_date, status_id, current_salary, expected_salary, notice_period, cover_letter) "
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, applications.getjobID());
            pstmt.setInt(2, applications.getcandidateID());
            pstmt.setTimestamp(3, new Timestamp(applications.getapplicationDate().getTime())); // convert Date to
                                                                                               // Timestamp for sql
            // ordinal will return the index of the enum statuses
            pstmt.setInt(4, applications.getStatus().ordinal() + 1);
            pstmt.setBigDecimal(5, applications.getCurrentSalary());
            pstmt.setBigDecimal(6, applications.getExpectedSalary());
            pstmt.setInt(7, applications.getnoticePeriod());
            pstmt.setString(8, applications.getcoverLetter());

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
        String sql = "UPDATE applications SET job_id = ?, candidate_id = ?, application_date = ?, status_id = ?, current_salary = ?, notice_period = ?, cover_letter = ? "
                +
                "WHERE application_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, applications.getjobID());
            pstmt.setInt(2, applications.getcandidateID());
            pstmt.setTimestamp(3, new Timestamp(applications.getapplicationDate().getTime())); // convert Date to
                                                                                               // Timestamp for sql
            pstmt.setInt(4, applications.getStatus().ordinal() + 1); // convert enum to string
            pstmt.setBigDecimal(5, applications.getCurrentSalary());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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

        application.setjobID(rs.getInt("job_id"));
        application.setcandidateID(rs.getInt("candidate_id"));
        application.setapplicationDate(rs.getTimestamp("application_date"));
        application.setstatus(rs.getString("status"));

        return application;
    }
}
