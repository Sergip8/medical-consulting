package com.consumed.medicalconsulting.models;

import com.consumed.medicalconsulting.models.Enums.AppointmentState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Table(name = "citas")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicalAppointments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn
    private Patient patient;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    private ConsultingRoom medicalConsulting;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    private Doctor doctor;
    private Instant createdDate;
    private LocalDateTime appointmentDate;
    private String response;
    @Enumerated(EnumType.STRING)
    private AppointmentState state;
}
