package com.example.patient.dao;

import com.example.patient.disease.DiseaseForm;
import com.example.patient.entity.Disease;

import java.util.List;

public interface IDiseaseDao {
    Disease save(Disease disease);

    List<Disease> getDiseases();

    List<Disease> getDiseases(DiseaseForm form);

    void insert(String name, Integer patientId);

    Disease findTopByOrderByDiseaseIdDesc();

    void update(Integer diseaseId, String name);

    void deleteById(Integer diseaseId);

    void deleteByIds(List<Integer> ids);

    void deleteByPatientId(Integer patientId);

    Disease findByDiseaseId(Integer diseaseId);

    List<String> getNames();
}
