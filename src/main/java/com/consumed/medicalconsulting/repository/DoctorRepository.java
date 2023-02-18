package com.consumed.medicalconsulting.repository;

import com.consumed.medicalconsulting.models.Doctor;
import com.consumed.medicalconsulting.models.Enums.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Optional<Doctor> findByIdentityNumber(String doctorCC);

    List<Doctor> findAllBySpecialization(Specialization specialization);

}
