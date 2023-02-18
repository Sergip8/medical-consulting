package com.consumed.medicalconsulting.dto;

import com.consumed.medicalconsulting.models.Enums.DocumentType;
import com.consumed.medicalconsulting.models.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest {
    private  String firstname;
    private  String lastname;
    private String identityNumber;
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;
    private String tel;

}
