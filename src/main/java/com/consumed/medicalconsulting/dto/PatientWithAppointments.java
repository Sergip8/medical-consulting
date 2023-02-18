package com.consumed.medicalconsulting.dto;

import com.consumed.medicalconsulting.models.Enums.DocumentType;
import com.consumed.medicalconsulting.models.MedicalAppointments;
import com.consumed.medicalconsulting.models.PersonalInfo;
import com.consumed.medicalconsulting.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;

import java.util.ArrayList;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class PatientWithAppointments {
    private Integer id;
    private  String firstname;
    private  String lastname;
    private String identityNumber;
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;
    private String tel;

    private List<MedicalAppointments> medicalAppointmentsList = new ArrayList<>();
}
