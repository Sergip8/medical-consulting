package com.consumed.medicalconsulting.service;

import com.consumed.medicalconsulting.dto.PatientRequest;
import com.consumed.medicalconsulting.dto.PatientResponse;
import com.consumed.medicalconsulting.dto.PatientWithAppointments;
import com.consumed.medicalconsulting.exception.SpringRedditException;
import com.consumed.medicalconsulting.models.MedicalAppointments;
import com.consumed.medicalconsulting.models.Patient;
import com.consumed.medicalconsulting.models.User;
import com.consumed.medicalconsulting.repository.AppointmentRepository;
import com.consumed.medicalconsulting.repository.PatientRepository;
import com.consumed.medicalconsulting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AppointmentRepository appointmentRepository;
    public void register(PatientRequest patientRequest) {
        patientRepository.save(mapToPatient(patientRequest));
    }
    private Patient mapToPatient(PatientRequest patientRequest){
        User patient = userRepository.findByEmail(jwtService.getCurrentUser())
                .orElseThrow(() -> new SpringRedditException("No encontro el usuario"));

            return Patient.builder()
                    .firstname(patientRequest.getFirstname())
                    .lastname(patientRequest.getLastname())
                    .identityNumber(patientRequest.getIdentityNumber())
                    .documentType(patientRequest.getDocumentType())
                    .tel(patientRequest.getTel())
                    .patientAuth(patient)
                    .build();
    }
    public PatientResponse getPatientByCC(String patientCC){
        Patient patient = patientRepository.findByIdentityNumber(patientCC)
                .orElseThrow(() -> new SpringRedditException("NO encontro el paciente"));
        return mapToPatientRes(patient);
    }
    private PatientResponse mapToPatientRes(Patient patient){
        return PatientResponse.builder()
                .firstname(patient.getFirstname())
                .lastname(patient.getLastname())
                .identityNumber(patient.getIdentityNumber())
                .documentType(patient.getDocumentType())
                .id(patient.getId())
                .tel(patient.getTel())
                .build();
    }
    public PatientResponse getPatientByCurrentUser(){
        Patient patient = patientRepository.findByPatientAuthEmail(jwtService.getCurrentUser())
                .orElseThrow(() -> new SpringRedditException("no hay paciente"));
        return mapToPatientRes(patient);
    }

    public PatientWithAppointments getPatientAppointments(String cc) {
        Patient patient = patientRepository.findByIdentityNumber(cc)
                .orElseThrow(() -> new SpringRedditException("no hay paciente"));

        return mapToPatientAppointments(patient);
    }

    private PatientWithAppointments mapToPatientAppointments(Patient patient) {

        //List<MedicalAppointments> medicalAppointments = appointmentRepository.findAllByPatientIdentityNumber(patient.getIdentityNumber());

        PatientWithAppointments patientWithAppointments = PatientWithAppointments.builder()
                .id(patient.getId())
                .firstname(patient.getFirstname())
                .lastname(patient.getLastname())
                .documentType(patient.getDocumentType())
                .identityNumber(patient.getIdentityNumber())
                .tel(patient.getTel())
                .medicalAppointmentsList(new ArrayList<>())
                .build();
        //patientWithAppointments.getMedicalAppointmentsList().add(medicalAppointments.get(0));
                return patientWithAppointments;
    }
}
