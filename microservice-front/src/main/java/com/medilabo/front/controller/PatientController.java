package com.medilabo.front.controller;

import com.medilabo.front.model.Patient;
import com.medilabo.front.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
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
}
