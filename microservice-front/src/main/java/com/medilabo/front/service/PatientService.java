package com.medilabo.front.service;

import com.medilabo.front.model.Patient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class PatientService {

    private final RestClient restClient;

    public PatientService(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<Patient> getAllPatients() {
        return restClient.get().uri("/patients").retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public Patient getPatientById(Long id) {
        return restClient.get().uri("/patients/{id}", id).retrieve()
                .body(Patient.class);
    }
}
