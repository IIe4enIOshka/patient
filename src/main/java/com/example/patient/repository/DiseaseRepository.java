package com.example.patient.repository;

import com.example.patient.entity.Disease;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {
    Disease save(Disease disease);

    @Modifying
    @Transactional
    @Query(value = "insert into disease (name, patient_id) values (:name, :patientId)", nativeQuery = true)
    void insert(String name, Integer patientId);

    @Modifying
    @Transactional
    @Query(value = "update disease set name = :name where disease_id = :diseaseId", nativeQuery = true)
    void update(Integer diseaseId, String name);

    @Modifying
    @Transactional
    @Query(value = "delete from disease where disease_id = :diseaseId", nativeQuery = true)
    void deleteById(Integer diseaseId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM disease WHERE disease_id in (:ids)", nativeQuery = true)
    void deleteByIds(List<Integer> ids);

    Disease findTopByOrderByDiseaseIdDesc();

    Disease findByDiseaseId(Integer diseaseId);

    @Query(value = "SELECT * FROM disease WHERE (name = :name OR :name IS NULL OR :name = '')", nativeQuery = true)
    List<Disease> getDisease(String name);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM disease WHERE patient_id = :patientId", nativeQuery = true)
    void deleteByPatientId(Integer patientId);

    @Query(value = "SELECT name FROM disease ORDER BY name", nativeQuery = true)
    List<String> getNames();
}
