package com.medilabo.front.service;

import com.medilabo.front.model.RiskResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class RiskService {

    private final RestClient restClient;

    public RiskService(RestClient patientRestClient) {
        this.restClient = restClient(patientRestClient);
    }

    private RestClient restClient(RestClient restClient) {
        return restClient;
    }

    public RiskResponse getRisk(Long patientId) {
        return restClient.get().uri("/assess/{id}", patientId)
                .retrieve().body(RiskResponse.class);
    }
}
