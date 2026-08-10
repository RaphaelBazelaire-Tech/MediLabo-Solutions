package com.medilabo.risque.service;

import com.medilabo.risque.model.Note;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class TriggerCounter {

    public long countTriggers(List<Note> notes) {
        String allNotes = notes.stream()
                .map(Note::getNote)
                .filter(Objects::nonNull)
                .reduce("", (a, b) -> a + " " + b)
                .toLowerCase();

        return TriggerTerms.TERMS.stream()
                .filter(allNotes::contains)
                .count();
    }
}
