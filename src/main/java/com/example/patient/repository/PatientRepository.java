package com.example.patient.repository;

import com.example.patient.entity.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient save(Patient patient);

    @Modifying
    @Transactional
    @Query(value = "insert into patient (name) values (:name)", nativeQuery = true)
    void insertPatient(String name);

    @Modifying
    @Transactional
    @Query(value = "update patient set name = :name where patient_id = :patientId", nativeQuery = true)
    void update(Integer patientId, String name);

    @Modifying
    @Transactional
    @Query(value = "delete from patient where patient_id = :patientId", nativeQuery = true)
    void deleteById(Integer patientId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM patient WHERE patient_id in (:ids)", nativeQuery = true)
    void deleteByIds(List<Integer> ids);

    Patient findTopByOrderByPatientIdDesc();

    Patient findByPatientId(Integer patientId);

    @Query(value = "SELECT * FROM patient WHERE (name = :name OR :name IS NULL OR :name = '')", nativeQuery = true)
    List<Patient> getPatients(String name);

    @Query(value = "SELECT name FROM patient ORDER BY name", nativeQuery = true)
    List<String> getNames();
}
