package com.consumed.medicalconsulting.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Table(name = "consultorios")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String number;
    private String type;
    private String description;
    private int isActive;
    @ManyToOne
    private MedicalCenter medicalCenter;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    private List<MedicalAppointments> medicalAppointmentsList;

}
