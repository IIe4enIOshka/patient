package com.example.patient.dao;

import com.example.patient.disease.DiseaseForm;
import com.example.patient.entity.Disease;
import com.example.patient.repository.DiseaseRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseDao implements IDiseaseDao {
    private final DiseaseRepository diseaseRepository;

    public DiseaseDao(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    @Override
    public Disease save(Disease disease) {
        return diseaseRepository.save(disease);
    }

    @Override
    public List<Disease> getDiseases() {
        return diseaseRepository.findAll();
    }

    @Override
    public List<Disease> getDiseases(DiseaseForm form) {
        String name = null;

        if (form.getNames() != null && form.getNames().size() > 0 && StringUtils.isNotBlank(form.getNames().get(0)))
            name = form.getNames().get(0);

        return diseaseRepository.getDisease(name);
    }

    @Override
    public void insert(String name, Integer patientId) {
        diseaseRepository.insert(name, patientId);
    }

    @Override
    public Disease findTopByOrderByDiseaseIdDesc() {
        return diseaseRepository.findTopByOrderByDiseaseIdDesc();
    }

    @Override
    public Disease findByDiseaseId(Integer diseaseId) {
        return diseaseRepository.findByDiseaseId(diseaseId);
    }

    @Override
    public List<String> getNames() {
        return diseaseRepository.getNames();
    }

    @Override
    public void update(Integer diseaseId, String name) {
        diseaseRepository.update(diseaseId, name);
    }

    @Override
    public void deleteById(Integer diseaseId) {
        diseaseRepository.deleteById(diseaseId);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        diseaseRepository.deleteByIds(ids);
    }

    @Override
    public void deleteByPatientId(Integer patientId) {
        diseaseRepository.deleteByPatientId(patientId);
    }
}
