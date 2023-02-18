package com.consumed.medicalconsulting.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Table(name = "centro_medico")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicalCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String address;
    private String tel;
    private String city;
    private String region;
    private String postalCode;
    @OneToMany(fetch = FetchType.LAZY)
    private List<ConsultingRoom> medicalColsultingList;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Doctor> doctors;
}
