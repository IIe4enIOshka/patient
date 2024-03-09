package com.example.patient.patient;

import com.example.patient.dao.IDiseaseDao;
import com.example.patient.dao.IPatientDao;
import com.example.patient.entity.Disease;
import com.example.patient.entity.Patient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatientEditController {
    @Autowired
    private IPatientDao patientDao;

    @Autowired
    private IDiseaseDao diseaseDao;

    @PostMapping("/patient/addDiseasePatient")
    public String addDiseasePatient(Patient patient) {
        patient.getDiseases().add(new Disease(patient));
        return "patient/patientEdit :: diseaseList";
    }

    @PostMapping("/patient/removeDiseasePatient")
    public String removeDiseasePatient(
            Patient patient,
            @RequestParam(required = false, value = "diseaseId") Integer diseaseId
    ) {
        patient.getDiseases().remove(diseaseId.intValue());
        return "patient/patientEdit :: diseaseList";
    }

    @RequestMapping(value = "/patient/patientEdit.html", method = RequestMethod.GET)
    public String showForm(
            Model model,
            @RequestParam(required = false) Integer patientId
    ) {
        Patient patient = null;

        if (patientId != null) {
            patient = patientDao.findByPatientId(patientId);
        }

        if (patient == null) {
            patient = new Patient();
        }

        model.addAttribute("patient", patient);
        return "patient/patientEdit";
    }

    @RequestMapping(value = "/patient/patientEdit.html", method = RequestMethod.POST, params = "!_cancel")
    public ResponseEntity<?> onSubmit(@Valid Patient patient, BindingResult bindingResult) {
        if (patient != null) {
            if (bindingResult.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors().get(0).getDefaultMessage());
            }

            if (patient.getPatientId() == null) {
//                patientDao.save(patient);
                patientDao.insert(patient.getName());
                patient.setPatientId(patientDao.findTopByOrderByPatientIdDesc().getPatientId());
            } else {
                patientDao.update(patient.getPatientId(), patient.getName());
            }

            diseaseDao.deleteByPatientId(patient.getPatientId());

            for (Disease disease : patient.getDiseases()) {
                diseaseDao.insert(disease.getName(), patient.getPatientId());
            }

            return ResponseEntity.status(HttpStatus.OK).body("Patient has been saved");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no Patient");
    }

    @RequestMapping(value = "/patient/patientEdit.html", method = RequestMethod.POST, params = "_cancel")
    public String onCancel(@ModelAttribute("command") Patient patient) {
        return "redirect:/patient/patients.html";
    }
}
