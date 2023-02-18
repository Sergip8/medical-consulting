package com.consumed.medicalconsulting.repository;

import com.consumed.medicalconsulting.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

    Optional<Patient> findByIdentityNumber(String patientCC);
    Optional<Patient> findByPatientAuthEmail(String email);
}
