package com.medilabo.risque.service;

import com.medilabo.risque.model.Note;
import com.medilabo.risque.model.Patient;
import com.medilabo.risque.model.RiskLevel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RiskAssessmentServiceTest {

    private final RiskAssessmentService service = new RiskAssessmentService(new TriggerCounter());

    private Patient patient(LocalDate birth, String gender) {
        Patient patient = new Patient();
        patient.setDateOfBirth(birth);
        patient.setGender(gender);
        return patient;
    }

    private Note note(String text) {
        Note note = new Note();
        note.setNote(text);
        return note;
    }

    @Test
    public void patient1TestNoneShouldBeNone() {
        Patient patient = patient(LocalDate.of(1966, 12, 31), "F");
        List<Note> notes = List.of(note("Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé"));
        assertEquals(RiskLevel.NONE, service.assessRisk(patient, notes));
    }

    @Test
    public void patient2TestBorderLineShouldBeBorderLine() {
        Patient patient = patient(LocalDate.of(1945, 6, 24), "M");
        List<Note> notes = List.of(
                note("Le patient déclare qu'il ressent beaucoup de stress au travail Il se plaint également que son audition est anormale dernièrement"),
                note("Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois Il remarque également que son audition continue d'être anormale"));
        assertEquals(RiskLevel.BORDERLINE, service.assessRisk(patient, notes));
    }

    @Test
    public void patient3TestInDangerShouldBeInDanger() {
        Patient patient = patient(LocalDate.of(2004, 6, 18), "M");
        List<Note> notes = List.of(
                note("Le patient déclare qu'il fume depuis peu"),
                note("Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière Il se plaint également de crises d'apnée respiratoire anormales Tests de laboratoire indiquant un taux de cholestérol LDL élevé"));
        assertEquals(RiskLevel.IN_DANGER, service.assessRisk(patient, notes));
    }

    @Test
    public void patient4TestEarlyOnsetShouldBeEarlyOnset() {
        Patient p = patient(LocalDate.of(2002, 6, 28), "F");
        List<Note> notes = List.of(
                note("Le patient déclare qu'il lui est devenu difficile de monter les escaliers Il se plaint également d'être essoufflé Tests de laboratoire indiquant que les anticorps sont élevés Réaction aux médicaments"),
                note("Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps"),
                note("Le patient déclare avoir commencé à fumer depuis peu Hémoglobine A1C supérieure au niveau recommandé"),
                note("Taille, Poids, Cholestérol, Vertige et Réaction"));
        assertEquals(RiskLevel.EARLY_ONSET, service.assessRisk(p, notes));
    }
}
