package com.consumed.medicalconsulting.dto;

import com.consumed.medicalconsulting.models.MedicalCenter;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultingRoomResponse {
    private Integer id;
    private String number;
    private String type;
    private String description;
    private int isActive;
}
