package com.medilabo.risque.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Patient {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
}
