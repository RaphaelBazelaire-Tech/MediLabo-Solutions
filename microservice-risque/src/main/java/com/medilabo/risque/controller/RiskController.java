package com.medilabo.risque.controller;

import com.medilabo.risque.client.GatewayClient;
import com.medilabo.risque.dto.RiskResponse;
import com.medilabo.risque.model.Note;
import com.medilabo.risque.model.Patient;
import com.medilabo.risque.model.RiskLevel;
import com.medilabo.risque.service.AgeCalculator;
import com.medilabo.risque.service.RiskAssessmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/assess")
public class RiskController {

    private final GatewayClient gatewayClient;
    private final RiskAssessmentService riskAssessmentService;

    public RiskController(GatewayClient gatewayClient, RiskAssessmentService riskAssessmentService) {
        this.gatewayClient = gatewayClient;
        this.riskAssessmentService = riskAssessmentService;
    }

    @GetMapping("/{patientId}")
    public RiskResponse assess(@PathVariable Long patientId) {
        Patient patient = gatewayClient.getPatient(patientId);
        List<Note> notes = gatewayClient.getNotesByPatientId(patientId.intValue());

        RiskLevel risk = riskAssessmentService.assessRisk(patient, notes);

        return new RiskResponse(
                patient.getId(),
                patient.getFirstName(),
                patient.getLastName(),
                AgeCalculator.calculateAge(patient.getDateOfBirth()),
                risk.getLabel());
    }
}
