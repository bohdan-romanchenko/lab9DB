package com.kpi.service;

import com.kpi.model.PaymentOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gleb on 06.12.16.
 */
@Service
public class PaymentOrderService {

    @Autowired
    Environment environment;

    @Autowired
    Connection connection;

    public List<PaymentOrder> getAll() {
        List<PaymentOrder> paymentOrders = new ArrayList<PaymentOrder>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from PAYMENT_ORDER");
            while (rs.next()) {
                PaymentOrder paymentOrder = new PaymentOrder();
                paymentOrder.setId(Long.parseLong(rs.getString("PAYMENT_ORDER_ID")));
                paymentOrder.setKekv(Long.parseLong(rs.getString("KEKV_ID")));
                paymentOrder.setBudgetaryInstitution(Long.parseLong(rs.getString("BUDGETARY_INSTITUTION_ID")));
                paymentOrder.setCommercialBank(Long.parseLong(rs.getString("COMMERCIAL_BANK_ID")));
                paymentOrder.setBasis(rs.getString("BASIS"));
                paymentOrder.setAmount(Float.parseFloat(rs.getString("AMOUNT")));
                paymentOrder.setCreated(rs.getDate("DATE_OF_CREATION"));
                paymentOrder.setReceiver(rs.getString("RECEIVER"));
                paymentOrders.add(paymentOrder);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return paymentOrders;
    }

    public PaymentOrder getById(Long paymentOrderId) {
        PaymentOrder paymentOrder = new PaymentOrder();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from PAYMENT_ORDER where PAYMENT_ORDER_ID=?");
            preparedStatement.setLong(1, paymentOrderId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                paymentOrder.setId(Long.parseLong(rs.getString("PAYMENT_ORDER_ID")));
                paymentOrder.setKekv(Long.parseLong(rs.getString("KEKV_ID")));
                paymentOrder.setBudgetaryInstitution(Long.parseLong(rs.getString("BUDGETARY_INSTITUTION_ID")));
                paymentOrder.setCommercialBank(Long.parseLong(rs.getString("COMMERCIAL_BANK_ID")));
                paymentOrder.setBasis(rs.getString("BASIS"));
                paymentOrder.setAmount(Float.parseFloat(rs.getString("AMOUNT")));
                paymentOrder.setCreated(rs.getDate("DATE_OF_CREATION"));
                paymentOrder.setReceiver(rs.getString("RECEIVER"));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }

        return paymentOrder;
    }

    public void update(PaymentOrder paymentOrder) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(
                "update PAYMENT_ORDER set KEKV_ID=?, BUDGETARY_INSTITUTION_ID =?, COMMERCIAL_BANK_ID=?, " +
                        "BASIS=?, AMOUNT=?, DATE_OF_CREATION=?, RECEIVER=? where PAYMENT_ORDER_ID=?");

        preparedStatement.setLong(1, paymentOrder.getKekv());
        preparedStatement.setLong(2, paymentOrder.getBudgetaryInstitution());
        preparedStatement.setLong(3, paymentOrder.getCommercialBank());
        preparedStatement.setString(4, paymentOrder.getBasis());
        preparedStatement.setFloat(5, paymentOrder.getAmount());
        preparedStatement.setDate(6, paymentOrder.getCreated());
        preparedStatement.setString(7, paymentOrder.getReceiver());
        preparedStatement.setLong(8, paymentOrder.getId());
        preparedStatement.executeUpdate();
    }

    public void insert(PaymentOrder paymentOrder) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO PAYMENT_ORDER(PAYMENT_ORDER_ID, KEKV_ID, BUDGETARY_INSTITUTION_ID, COMMERCIAL_BANK_ID," +
                        "BASIS, AMOUNT, DATE_OF_CREATION, RECEIVER) " +
                        "VALUES (?,?,?,?,?,?,?,?)");

        preparedStatement.setLong(1, paymentOrder.getId());
        preparedStatement.setLong(2, paymentOrder.getKekv());
        preparedStatement.setLong(3, paymentOrder.getBudgetaryInstitution());
        preparedStatement.setLong(4, paymentOrder.getCommercialBank());
        preparedStatement.setString(5, paymentOrder.getBasis());
        preparedStatement.setFloat(6, paymentOrder.getAmount());
        preparedStatement.setDate(7, paymentOrder.getCreated());
        preparedStatement.setString(8, paymentOrder.getReceiver());
        preparedStatement.executeUpdate();
    }

    public void delete(Long id) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("delete from PAYMENT_ORDER where PAYMENT_ORDER_ID=?");
        preparedStatement.setLong(1, id);
        preparedStatement.execute();
    }

    public Integer getMaxId() throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT MAX(PAYMENT_ORDER_ID) FROM PAYMENT_ORDER");
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next())
            return rs.getInt("MAX(PAYMENT_ORDER_ID)");
        else
            return null;
    }
}
