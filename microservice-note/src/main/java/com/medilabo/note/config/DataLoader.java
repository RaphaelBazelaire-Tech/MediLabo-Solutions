package com.medilabo.note.config;

import com.medilabo.note.model.Note;
import com.medilabo.note.repository.NoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initNotes(NoteRepository noteRepository) {
        return args -> {

            if (noteRepository.count() == 0) {

                noteRepository.save(new Note(1, "TestNone",
                        "Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé"));

                noteRepository.save(new Note(2, "TestBorderline",
                        "Le patient déclare qu'il ressent beaucoup de stress au travail Il se plaint également que son audition est anormale dernièrement"));

                noteRepository.save(new Note(2, "TestBorderline",
                        "Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois Il remarque également que son audition continue d'être anormale"));

                noteRepository.save(new Note(3, "TestInDanger",
                        "Le patient déclare qu'il fume depuis peu"));

                noteRepository.save(new Note(3, "TestInDanger",
                        "Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière Il se plaint également de crises d'apnée respiratoire anormales Tests de laboratoire indiquant un taux de cholestérol LDL élevé"));

                noteRepository.save(new Note(4, "TestEarlyOnset",
                        "Le patient déclare qu'il lui est devenu difficile de monter les escaliers Il se plaint également d'être essoufflé Tests de laboratoire indiquant que les anticorps sont élevés Réaction aux médicaments"));

                noteRepository.save(new Note(4, "TestEarlyOnset",
                        "Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps"));

                noteRepository.save(new Note(4, "TestEarlyOnset",
                        "Le patient déclare avoir commencé à fumer depuis peu Hémoglobine A1C supérieure au niveau recommandé"));

                noteRepository.save(new Note(4, "TestEarlyOnset",
                        "Taille, Poids, Cholestérol, Vertige et Réaction"));
            }
        };
    }
}
