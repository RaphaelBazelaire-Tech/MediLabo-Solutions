package com.medilabo.risque.service;

import java.time.LocalDate;
import java.time.Period;

public final class AgeCalculator {

    private AgeCalculator() {}

    public static int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
