package com.consumed.medicalconsulting.models;

import com.consumed.medicalconsulting.models.Enums.DocumentType;
import com.consumed.medicalconsulting.models.Enums.Specialization;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Table(name = "doctores")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private  String firstname;
    private  String lastname;
    private String identityNumber;
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;
    private Specialization specialization;
    private String tel;
    @OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "id", referencedColumnName = "id")
    private User doctorAuth;
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    private PersonalInfo personalInfo;
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    private DoctorProfessionalInfo professionalInfo;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    private List<MedicalAppointments> medicalAppointmentsList;


}
