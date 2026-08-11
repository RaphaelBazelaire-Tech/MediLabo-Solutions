package com.medilabo.risque.service;

import com.medilabo.risque.model.Note;
import com.medilabo.risque.model.Patient;
import com.medilabo.risque.model.RiskLevel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskAssessmentService {

    private final TriggerCounter triggerCounter;

    public RiskAssessmentService(TriggerCounter triggerCounter) {
        this.triggerCounter = triggerCounter;
    }

    public RiskLevel assessRisk(Patient patient, List<Note> notes) {
        long triggers = triggerCounter.countTriggers(notes);
        int age = AgeCalculator.calculateAge(patient.getDateOfBirth());
        boolean isMale = "M".equalsIgnoreCase(patient.getGender());

        if (triggers == 0) {
            return RiskLevel.NONE;
        }

        if (age > 30) {
            if (triggers >= 8) return RiskLevel.EARLY_ONSET;
            if (triggers >= 6) return RiskLevel.IN_DANGER;
            if (triggers >= 2) return RiskLevel.BORDERLINE;
            return RiskLevel.NONE;

        } else {
            if (isMale) {
                if (triggers >= 5) return RiskLevel.EARLY_ONSET;
                if (triggers >= 3) return RiskLevel.IN_DANGER;

            } else {
                if (triggers >= 7) return RiskLevel.EARLY_ONSET;
                if (triggers >= 4) return RiskLevel.IN_DANGER;
            }
        }
        return RiskLevel.NONE;
    }
}
