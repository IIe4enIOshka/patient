package com.example.patient.disease;

import com.example.patient.dao.IDiseaseDao;
import com.example.patient.entity.Disease;
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
public class DiseaseController {
    @Autowired
    private IDiseaseDao diseaseDao;

    @ModelAttribute("command")
    private DiseaseForm getForm() {
        return new DiseaseForm();
    }

    @RequestMapping("/disease/diseases.html")
    public String showData(
            @ModelAttribute("command") DiseaseForm form,
            Model model) {
        List<Disease> diseases = diseaseDao.getDiseases(form);
        List<String> names = diseaseDao.getNames();

        model.addAttribute("command", form);
        model.addAttribute("list", diseases);
        model.addAttribute("names", names);

        return "disease/diseases";
    }

    @RequestMapping("/disease/diseaseDelete.html")
    public ResponseEntity<?> delete(@RequestParam(required = false, value = "diseaseIds") List<Integer> diseaseIds) {
        diseaseDao.deleteByIds(diseaseIds);
        return ResponseEntity.status(HttpStatus.OK).body("Disease has been deleted");
    }
}
