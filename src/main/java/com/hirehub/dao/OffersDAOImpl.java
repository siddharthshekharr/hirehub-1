package com.hirehub.dao;

import com.hirehub.model.Offers;
import com.hirehub.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OffersDAOImpl implements OffersDAO {

    private Connection connection;

    public OffersDAOImpl() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void add(Offers offers) {
        String sql = "INSERT INTO offers (offer_id, application_id, salary, start_date, status) VALUES (?, ?, ?, ?, ?)  ";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, offers.getofferID());
            pstmt.setInt(2, offers.getapplicationID());
            pstmt.setBigDecimal(3, offers.getsalary());
            pstmt.setDate(4, new java.sql.Date(offers.getstartDate().getTime()));
            pstmt.setString(5, offers.getstatus());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Offers offers) {
        String sql = "UPDATE offers SET application_id = ?, salary = ?, start_date = ?, status = ? WHERE offer_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, offers.getapplicationID());
            pstmt.setBigDecimal(2, offers.getsalary());
            pstmt.setDate(3, new java.sql.Date(offers.getstartDate().getTime()));
            pstmt.setString(4, offers.getstatus());
            pstmt.setInt(5, offers.getofferID());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Offers offers) {
        String sql = "DELETE FROM offers WHERE offer_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, offers.getofferID());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Offers getByID(int id) {
        String sql = "SELECT FROM offers WHERE offer_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractOffersQuestionsFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            ;
        }
        return null;
    }

    @Override
    public List<Offers> getAll() {
        List<Offers> offersList = new ArrayList<>();
        String sql = "SELECT * FROM offers";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                offersList.add(extractOffersQuestionsFromResultSet(rs)); // Use screeningsList here
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return offersList;

    }

    private Offers extractOffersQuestionsFromResultSet(ResultSet rs) throws SQLException {
        Offers offers = new Offers();

        offers.setofferID(rs.getInt("offer_id"));
        offers.setapplicationID(rs.getInt("application_id"));
        offers.setsalary(rs.getBigDecimal("salary"));
        offers.setstartDate(rs.getDate("start_date"));
        offers.setstatus(rs.getString("status"));

        return offers;
    }
}