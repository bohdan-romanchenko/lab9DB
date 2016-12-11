package com.kpi.service;

import com.kpi.model.Estimate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class EstimateService {

    @Autowired
    Environment environment;
    @Autowired
    Connection connection;

    public List<Estimate> getAll() {
        List<Estimate> estimates = new ArrayList<Estimate>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from ESTIMATE");
            while (rs.next()) {
                Estimate estimate = new Estimate();
                estimate.setId(Long.parseLong(rs.getString("estimate_id")));
                estimate.setKekv(Long.parseLong(rs.getString("kekv_id")));
                estimate.setBudgetaryInstitution(Long.parseLong(rs.getString("budgetary_institution_id")));
                estimate.setYear(Integer.parseInt(rs.getString("year")));
                estimate.setSpendingLimit(Float.parseFloat(rs.getString("spending_limit")));
                estimates.add(estimate);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return estimates;
    }

    public Estimate getById(Long estimateId) {
        Estimate estimate = new Estimate();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from ESTIMATE where estimate_id=?");
            preparedStatement.setString(1, estimateId.toString());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                estimate.setId(Long.parseLong(rs.getString("estimate_id")));
                estimate.setKekv(Long.parseLong(rs.getString("kekv_id")));
                estimate.setBudgetaryInstitution(Long.parseLong(rs.getString("budgetary_institution_id")));
                estimate.setYear(Integer.parseInt(rs.getString("year")));
                estimate.setSpendingLimit(Float.parseFloat(rs.getString("spending_limit")));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }

        return estimate;
    }

    public void update(Estimate estimate) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(
                "update ESTIMATE set kekv_id=?, budgetary_institution_id=?, year=?, spending_limit=? where estimate_id=?");

        preparedStatement.setLong(1, estimate.getKekv());
        preparedStatement.setLong(2, estimate.getBudgetaryInstitution());
        preparedStatement.setInt(3, estimate.getYear());
        preparedStatement.setFloat(4, estimate.getSpendingLimit());
        preparedStatement.setLong(5, estimate.getId());
        preparedStatement.executeUpdate();
    }

    public void insert(Estimate estimate) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO ESTIMATE(estimate_id, kekv_id, budgetary_institution_id, year, spending_limit) " +
                        "VALUES (?,?,?,?,?)");

        preparedStatement.setLong(1, estimate.getId());
        preparedStatement.setLong(2, estimate.getKekv());
        preparedStatement.setLong(3, estimate.getBudgetaryInstitution());
        preparedStatement.setInt(4, estimate.getYear());
        preparedStatement.setFloat(5, estimate.getSpendingLimit());
        preparedStatement.executeUpdate();
    }

    public void check(Estimate estimate) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "select estimate_id from ESTIMATE where estimate_id = " + estimate.getId());
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            update(estimate);
        else
            insert(estimate);

    }

    public void delete(Long id) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("delete from ESTIMATE where estimate_id=?");
        preparedStatement.setLong(1, id);
        preparedStatement.execute();
    }

    public Integer getMaxId() throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT MAX(estimate_id) FROM ESTIMATE");
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next())
            return rs.getInt("MAX(estimate_id)");
        else
            return null;
    }
}
