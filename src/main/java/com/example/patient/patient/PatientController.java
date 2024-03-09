package com.example.patient.patient;

import com.example.patient.dao.IDiseaseDao;
import com.example.patient.dao.IPatientDao;
import com.example.patient.entity.Patient;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PatientController {
    @Autowired
    private IPatientDao patientDao;

    @Autowired
    private IDiseaseDao diseaseDao;

    @ModelAttribute("command")
    public PatientForm getForm() {
        return new PatientForm();
    }

    @RequestMapping({
            "/",
            "/patient/patients.html"
    })
    public String showData(
            @ModelAttribute("command") PatientForm form,
            HttpServletRequest request,
            Model model) {
        List<Patient> patients = patientDao.getPatients(form);
        List<String> names = patientDao.getNames();

        model.addAttribute("command", form);
        model.addAttribute("list", patients);
        model.addAttribute("names", names);

        return "patient/patients";
    }

    @RequestMapping("/patient/patientDelete.html")
    public ResponseEntity<?> delete(@RequestParam(required = false, value = "patientIds") List<Integer> patientIds) {
        for (Integer patientId : patientIds) {
            Patient patient = patientDao.findByPatientId(patientId);

            if (patient != null) {
                diseaseDao.deleteByPatientId(patient.getPatientId());
            }
        }

        patientDao.deleteByIds(patientIds);
        return ResponseEntity.status(HttpStatus.OK).body("Patient has been deleted");
    }
}
