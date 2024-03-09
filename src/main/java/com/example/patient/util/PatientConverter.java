package com.example.patient.util;

import com.example.patient.dao.IPatientDao;
import com.example.patient.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PatientConverter implements Converter<Integer, Patient> {
    @Autowired
    private IPatientDao patientDao;

    @Override
    public Patient convert(Integer patientId) {
        return patientDao.findByPatientId(patientId);
    }
}