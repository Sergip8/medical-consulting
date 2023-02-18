package com.consumed.medicalconsulting.dto;

import com.consumed.medicalconsulting.models.Enums.DocumentType;
import com.consumed.medicalconsulting.models.Enums.ECivil;
import com.consumed.medicalconsulting.models.Enums.Gender;
import com.consumed.medicalconsulting.models.Enums.Specialization;
import com.consumed.medicalconsulting.models.MedicalCenter;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequest {
    private  String firstname;
    private  String lastname;
    private String identityNumber;
    private DocumentType documentType;
    private String tel;
    private String professionalId;
    private Specialization specialization;
    private String medicalCenterName;
    private Date hireDate;
    private String address;
    private Date birthDate;
    private ECivil eCivil;
    private Gender gender;
}