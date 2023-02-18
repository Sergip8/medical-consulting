package com.consumed.medicalconsulting.dto;

import com.consumed.medicalconsulting.models.Enums.DocumentType;
import com.consumed.medicalconsulting.models.Enums.Specialization;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse {
    private Integer id;
    private String firstname;
    private String lastname;
    private String identityNumber;
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;
    private String tel;
    @Enumerated(EnumType.STRING)
    private Specialization specialization;
}