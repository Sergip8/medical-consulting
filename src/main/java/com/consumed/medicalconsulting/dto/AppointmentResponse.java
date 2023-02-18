package com.consumed.medicalconsulting.dto;

import com.consumed.medicalconsulting.models.Enums.AppointmentState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {
    private Long id;
    private String type;
    private String description;
    private Instant createdDate;
    private LocalDateTime appointmentDate;
    private String response;
    private AppointmentState state;
}
