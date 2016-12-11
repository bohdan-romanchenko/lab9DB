package com.kpi.service;

import com.kpi.model.BudgetaryInstitution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class BudgetaryInstitutionService {

    @Autowired
    Environment environment;
    @Autowired
    Connection connection;

    public List<BudgetaryInstitution> getAll() {
        List<BudgetaryInstitution> budgetaryInstitutions = new ArrayList<BudgetaryInstitution>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from BUDGETARY_INSTITUTION");
            while (rs.next()) {
                BudgetaryInstitution loadType = new BudgetaryInstitution();
                loadType.setId((long) Integer.parseInt(rs.getString("id")));
                loadType.setName(rs.getString("name"));
                budgetaryInstitutions.add(loadType);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return budgetaryInstitutions;
    }

    public BudgetaryInstitution getById(Integer id) {
        BudgetaryInstitution budgetaryInstitution = new BudgetaryInstitution();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from BUDGETARY_INSTITUTION where id=?");
            preparedStatement.setString(1, id.toString());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                budgetaryInstitution.setId((long) Integer.parseInt(rs.getString("id")));
                budgetaryInstitution.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }

        return budgetaryInstitution;
    }

    public void update(BudgetaryInstitution budgetaryInstitution) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(
                "update BUDGETARY_INSTITUTION set name =? where id=?");

        preparedStatement.setString(1, budgetaryInstitution.getName());
        preparedStatement.setLong(2, budgetaryInstitution.getId());
        preparedStatement.executeUpdate();
    }

    public void insert(BudgetaryInstitution budgetaryInstitution) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO BUDGETARY_INSTITUTION(id, name) " +
                        "VALUES (?,?)");

        preparedStatement.setLong(1, budgetaryInstitution.getId());
        preparedStatement.setString(2, budgetaryInstitution.getName());
        preparedStatement.executeUpdate();
    }

    public void delete(Integer id) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("delete from BUDGETARY_INSTITUTION where id=?");
        preparedStatement.setLong(1, id);
        preparedStatement.execute();
    }

    public Long getMaxId() throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT MAX(id) FROM BUDGETARY_INSTITUTION");
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next())
            return rs.getLong("MAX(id)");
        else
            return null;
    }
}
