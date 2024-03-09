package com.example.patient.dao;

import com.example.patient.entity.Patient;
import com.example.patient.patient.PatientForm;
import com.example.patient.repository.PatientRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientDao implements IPatientDao {
    private final PatientRepository patientRepository;

    public PatientDao(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    @Override
    public List<Patient> getPatients(PatientForm form) {
        String name = null;

        if (form.getNames() != null && form.getNames().size() > 0 && StringUtils.isNotBlank(form.getNames().get(0)))
            name = form.getNames().get(0);

        return patientRepository.getPatients(name);
    }

    @Override
    public void insert(String name) {
        patientRepository.insertPatient(name);
    }

    @Override
    public Patient findTopByOrderByPatientIdDesc() {
        return patientRepository.findTopByOrderByPatientIdDesc();
    }

    @Override
    public Patient findByPatientId(Integer patientId) {
        return patientRepository.findByPatientId(patientId);
    }

    @Override
    public void update(Integer patientId, String name) {
        patientRepository.update(patientId, name);
    }

    @Override
    public void deleteById(Integer patientId) {
        patientRepository.deleteById(patientId);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        patientRepository.deleteByIds(ids);
    }

    @Override
    public List<String> getNames() {
        return patientRepository.getNames();
    }
}
