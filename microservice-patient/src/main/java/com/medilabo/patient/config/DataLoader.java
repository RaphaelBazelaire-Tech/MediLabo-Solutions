package com.medilabo.patient.config;

import com.medilabo.patient.model.Gender;
import com.medilabo.patient.model.Patient;
import com.medilabo.patient.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initData(PatientRepository patientRepository) {
        return args -> {
            if (patientRepository.count() == 0) {
                patientRepository.save(new Patient("Test", "TestNone",
                        LocalDate.of(1966, 12, 31), Gender.F, "1 Brookside St", "100-222-3333"));

                patientRepository.save(new Patient("Test", "TestBorderline",
                        LocalDate.of(1945, 6, 24), Gender.M, "2 High St", "200-333-4444"));

                patientRepository.save(new Patient("Test", "TestInDanger",
                        LocalDate.of(2004, 6, 18), Gender.M, "3 Club Road", "300-444-5555"));

                patientRepository.save(new Patient("Test", "TestEarlyOnset",
                        LocalDate.of(2002, 6, 28), Gender.F, "4 Valley Dr", "400-555-6666"));
            }
        };
    }
}
