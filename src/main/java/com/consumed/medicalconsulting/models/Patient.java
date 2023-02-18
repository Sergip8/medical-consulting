package com.consumed.medicalconsulting.models;

import com.consumed.medicalconsulting.models.Enums.DocumentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Table(name = "pacientes")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private  String firstname;
    private  String lastname;
    private String identityNumber;
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;
    private String tel;
    @OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "id", referencedColumnName = "id")
    private User patientAuth;
    @OneToOne
    private PersonalInfo personalInfo;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    private List<MedicalAppointments> medicalAppointmentsList;

}
