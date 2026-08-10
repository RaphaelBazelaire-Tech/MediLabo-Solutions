package com.medilabo.front.service;

import com.medilabo.front.model.Note;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class NoteService {

    private final RestClient restClient;

    public NoteService(RestClient patientRestClient) {
        this.restClient = patientRestClient;
    }

    public List<Note> getNotesByPatientId(Integer patientId) {
        return restClient.get().uri("/notes/patient/{id}", patientId).retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public Note addNote(Note note) {
        return restClient.post().uri("/notes")
                .body(note).retrieve().body(Note.class);
    }
}
