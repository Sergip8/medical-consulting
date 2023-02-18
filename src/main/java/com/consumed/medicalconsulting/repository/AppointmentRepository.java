package com.consumed.medicalconsulting.repository;

import com.consumed.medicalconsulting.models.MedicalAppointments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<MedicalAppointments, Long> {
    Optional<List<MedicalAppointments>> findAllByPatientIdentityNumber(String identityNumber);

}
