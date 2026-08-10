package com.medilabo.front.model;

import lombok.Data;

@Data
public class Note {

    private String id;
    private Integer patientId;
    private String patient;
    private String note;
}
