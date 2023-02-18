package com.consumed.medicalconsulting.repository;

import com.consumed.medicalconsulting.models.MedicalCenter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicalCenterRepository extends JpaRepository<MedicalCenter, Integer> {
    Optional<MedicalCenter> findByName(String name);
}
