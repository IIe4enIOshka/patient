package com.example.patient.dao;

import com.example.patient.entity.Patient;
import com.example.patient.patient.PatientForm;

import java.util.List;

public interface IPatientDao {
    Patient save(Patient patient);

    List<Patient> getPatients();

    List<Patient> getPatients(PatientForm form);

    void insert(String name);

    Patient findTopByOrderByPatientIdDesc();

    Patient findByPatientId(Integer patientId);

    void update(Integer patientId, String name);

    void deleteById(Integer patientId);

    void deleteByIds(List<Integer> ids);

    List<String> getNames();
}
