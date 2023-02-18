package com.consumed.medicalconsulting.dto;


import com.consumed.medicalconsulting.models.Enums.AppointmentState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequest {
    private String type;
    private String description;
    private String patientCC;
    private Integer medicalConsultingId;
    private Integer doctorId;
    private Instant createdDate;
    private LocalDateTime appointmentDate;


    private String address;
    private String medicalCenterName;
}
