package com.medilabo.risque.client;

import com.medilabo.risque.model.Note;
import com.medilabo.risque.model.Patient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class GatewayClient {

    private final RestClient restClient;

    public GatewayClient(RestClient gatewayRestClient) {
        this.restClient = gatewayRestClient;
    }

    public Patient getPatient(Long id) {
        return restClient.get().uri("/patients/{id}", id)
                .retrieve().body(Patient.class);
    }

    public List<Note> getNotesByPatientId(Integer patientId) {
        return restClient.get().uri("/notes/patient/{id}", patientId)
                .retrieve().body(new ParameterizedTypeReference<>() {});
    }
}
