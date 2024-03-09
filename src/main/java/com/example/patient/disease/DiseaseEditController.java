package com.example.patient.disease;

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

import java.util.List;

@Controller
public class DiseaseEditController {
    @Autowired
    private IPatientDao patientDao;
    @Autowired
    private IDiseaseDao diseaseDao;

    @RequestMapping(value = "/disease/diseaseEdit.html", method = RequestMethod.GET)
    public String showForm(
            Model model,
            @RequestParam(required = false) Integer diseaseId
    ) {
        Disease disease = null;

        if (diseaseId != null) {
            disease = diseaseDao.findByDiseaseId(diseaseId);
        }

        if (disease == null) {
            disease = new Disease();
        }

        List<Patient> patients = patientDao.getPatients();

        model.addAttribute("disease", disease);
        model.addAttribute("patients", patients);

        return "disease/diseaseEdit";
    }

    @RequestMapping(value = "/disease/diseaseEdit.html", method = RequestMethod.POST, params = "!_cancel")
    public ResponseEntity<?> onSubmit(
            @Valid Disease disease,
            BindingResult bindingResult) {
        if (disease != null) {
            if (disease.getPatient() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Patient is required.");
            }

            if (bindingResult.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors().get(0).getDefaultMessage());
            }

            if (disease.getDiseaseId() == null) {
//                diseaseDao.save(patient);
                diseaseDao.insert(disease.getName(), disease.getPatientId());
            } else {
                diseaseDao.update(disease.getDiseaseId(), disease.getName());
            }

            return ResponseEntity.status(HttpStatus.OK).body("Disease has been saved");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no Disease");
    }

    @RequestMapping(value = "/disease/diseaseEdit.html", method = RequestMethod.POST, params = "_cancel")
    public String onCancel(@ModelAttribute("command") Disease disease) {
        return "redirect:/disease/diseases.html";
    }
}
