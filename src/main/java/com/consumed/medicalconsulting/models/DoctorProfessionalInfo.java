package com.consumed.medicalconsulting.models;

import com.consumed.medicalconsulting.models.Enums.Specialization;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Table(name = "informacion-profesional")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorProfessionalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String professionalId;
    @ManyToOne(fetch = FetchType.LAZY)
    private MedicalCenter medicalCenter;
    private Date hireDate;

}
