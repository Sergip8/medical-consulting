package com.consumed.medicalconsulting.models;

import com.consumed.medicalconsulting.models.Enums.DocumentType;
import com.consumed.medicalconsulting.models.Enums.ECivil;
import com.consumed.medicalconsulting.models.Enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Table(name = "informacion_personal")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private Date birthDate;
    private ECivil eCivil;
    private Gender gender;



}
