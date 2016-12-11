package com.kpi.model;

import lombok.Data;

@Data
public class Estimate {

    private Long id;
    private Long kekv;
    private Long budgetaryInstitution;
    private Integer year;
    private Float spendingLimit;

}
