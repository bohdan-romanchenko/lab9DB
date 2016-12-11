package com.kpi.model;

import lombok.Data;

import java.util.Currency;
import java.sql.Date;

@Data
public class PaymentOrder {

    private Long id;
    private Long kekv;
    private Long budgetaryInstitution;
    private Long commercialBank;
    private String basis;
    private Float amount;
    private Date created;
    private String receiver;

}
