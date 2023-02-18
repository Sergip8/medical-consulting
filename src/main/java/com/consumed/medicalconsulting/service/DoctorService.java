package com.consumed.medicalconsulting.service;

import com.consumed.medicalconsulting.dto.DoctorRequest;
import com.consumed.medicalconsulting.dto.DoctorResponse;
import com.consumed.medicalconsulting.exception.SpringRedditException;
import com.consumed.medicalconsulting.models.*;
import com.consumed.medicalconsulting.models.Enums.Specialization;
import com.consumed.medicalconsulting.repository.DoctorRepository;
import com.consumed.medicalconsulting.repository.MedicalCenterRepository;
import com.consumed.medicalconsulting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final MedicalCenterRepository medicalCenterRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public void register(DoctorRequest doctorRequest) {

       doctorRepository.save(mapToDoctor(doctorRequest));
    }

    private Doctor mapToDoctor(DoctorRequest doctorRequest){
       User doctor = userRepository.findByEmail(jwtService.getCurrentUser())
                .orElseThrow(() -> new SpringRedditException("No encontro el usuario"));
        return Doctor.builder()
                .firstname(doctorRequest.getFirstname())
                .lastname(doctorRequest.getLastname())
                .identityNumber(doctorRequest.getIdentityNumber())
                .documentType(doctorRequest.getDocumentType())
                .tel(doctorRequest.getTel())
                .doctorAuth(doctor)
                .specialization(doctorRequest.getSpecialization())
                .personalInfo(mapToPersonalInfo(doctorRequest))
                .professionalInfo(mapToDProInfo(doctorRequest))
                .build();

    }
    private DoctorProfessionalInfo mapToDProInfo(DoctorRequest doctorRequest){
        MedicalCenter medicalCenter = medicalCenterRepository.findByName(doctorRequest.getMedicalCenterName())
                .orElseThrow(() -> new SpringRedditException("No hay centro medico"));
        return DoctorProfessionalInfo.builder()
                .professionalId(doctorRequest.getProfessionalId())
                .hireDate(doctorRequest.getHireDate())
                .medicalCenter(medicalCenter)
                .build();
    }
    private PersonalInfo mapToPersonalInfo(DoctorRequest doctorRequest){
        return PersonalInfo.builder()
                .address(doctorRequest.getAddress())
                .gender(doctorRequest.getGender())
                .eCivil(doctorRequest.getECivil())
                .birthDate(doctorRequest.getBirthDate())
                .build();
    }
    public DoctorResponse getDoctorByCC(String doctorCC){
        Doctor doctor =  doctorRepository.findByIdentityNumber(doctorCC)
                .orElseThrow(() -> new SpringRedditException("NO encontro el dotor"));
        return mapToDoctorRes(doctor);
    }
    private DoctorResponse mapToDoctorRes(Doctor doctor){
        return DoctorResponse.builder()
                .firstname(doctor.getFirstname())
                .lastname(doctor.getLastname())
                .identityNumber(doctor.getIdentityNumber())
                .documentType(doctor.getDocumentType())
                .id(doctor.getId())
                .tel(doctor.getTel())
                .specialization(doctor.getSpecialization())
                .build();

    }
    public List<DoctorResponse> getDoctorBySpecialization(Specialization specialization){
        List<Doctor> doctorList = doctorRepository.findAllBySpecialization(specialization);

        return doctorList.stream().map(this::mapToDoctorRes).toList();
    }
}
