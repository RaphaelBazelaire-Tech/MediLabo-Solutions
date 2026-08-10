package com.medilabo.front.controller;

import com.medilabo.front.model.Note;
import com.medilabo.front.model.Patient;
import com.medilabo.front.service.NoteService;
import com.medilabo.front.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatientController {

    private final PatientService patientService;
    private final NoteService noteService;

    public PatientController(PatientService patientService, NoteService noteService) {
        this.patientService = patientService;
        this.noteService = noteService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/patients";
    }

    @GetMapping("/patients")
    public String listPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patients";
    }

    @GetMapping("/patients/{id}")
    public String patientDetail(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientService.getPatientById(id));
        model.addAttribute("notes", noteService.getNotesByPatientId(id.intValue()));
        model.addAttribute("newNote", new Note());
        return "patient-detail";
    }

    @GetMapping("/patients/add")
    public String addForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient-add";
    }

    @PostMapping("/patients/add")
    public String addPatient(@ModelAttribute Patient patient) {
        patientService.createPatient(patient);
        return "redirect:/patients";
    }

    @GetMapping("/patients/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientService.getPatientById(id));
        return "patient-edit";
    }

    @PostMapping("/patients/{id}/update")
    public String updatePatient(@PathVariable Long id, @ModelAttribute Patient patient) {
        patientService.updatePatient(id, patient);
        return "redirect:/patients/" + id;
    }

    @PostMapping("/patients/{id}/notes")
    public String addNote(@PathVariable Long id, @ModelAttribute("newNote") Note note, @RequestParam String patientName) {
        note.setPatientId(id.intValue());
        note.setPatient(patientName);
        noteService.addNote(note);
        return "redirect:/patients/" + id;
    }
}
