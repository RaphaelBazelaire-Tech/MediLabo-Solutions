package com.medilabo.risque.dto;

public record RiskResponse(
        Long patientId,
        String firstName,
        String lastName,
        int age,
        String riskLevel) {
}
