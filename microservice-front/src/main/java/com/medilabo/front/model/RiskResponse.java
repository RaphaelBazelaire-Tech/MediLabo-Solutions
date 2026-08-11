package com.medilabo.front.model;

import lombok.Data;

@Data
public class RiskResponse {

    private Long patientId;
    private String firstName;
    private String lastName;
    private int age;
    private String riskLevel;
}
