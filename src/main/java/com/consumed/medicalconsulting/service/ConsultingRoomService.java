package com.consumed.medicalconsulting.service;

import com.consumed.medicalconsulting.dto.ConsultingRoomRequest;
import com.consumed.medicalconsulting.dto.ConsultingRoomResponse;
import com.consumed.medicalconsulting.exception.SpringRedditException;
import com.consumed.medicalconsulting.models.ConsultingRoom;
import com.consumed.medicalconsulting.models.MedicalCenter;
import com.consumed.medicalconsulting.repository.ConsultingRoomRepository;
import com.consumed.medicalconsulting.repository.MedicalCenterRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ConsultingRoomService {

    private final ConsultingRoomRepository consultingRoomRepository;
    private final MedicalCenterRepository medicalCenterRepository;

    public void createConsultingRoom(ConsultingRoomRequest roomRequest){
        consultingRoomRepository.save(mapToConsultingRoom(roomRequest));
    }
    private ConsultingRoom mapToConsultingRoom(ConsultingRoomRequest roomRequest){
        System.out.println(roomRequest.getMedicalCenterName());
        MedicalCenter medicalCenter = medicalCenterRepository.findByName(roomRequest.getMedicalCenterName())
                .orElseThrow(() -> new SpringRedditException("No hay centro medico"));

        return ConsultingRoom.builder()
                .number(roomRequest.getNumber())
                .description(roomRequest.getDescription())
                .type(roomRequest.getType())
                .isActive(1)
                .medicalCenter(medicalCenter)
                .build();
    }
    public ConsultingRoomResponse getConsultingRoomById(Integer id){
        ConsultingRoom consultingRoom = consultingRoomRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No encontro consultorio por id"));
        return mapToResponse(consultingRoom);
    }
    private ConsultingRoomResponse mapToResponse(ConsultingRoom consultingRoom){
        return ConsultingRoomResponse.builder()
                .number(consultingRoom.getNumber())
                .type(consultingRoom.getType())
                .description(consultingRoom.getDescription())
                .id(consultingRoom.getId())
                .isActive(consultingRoom.getIsActive())
                .build();
    }
    public List<ConsultingRoomResponse> getConsultingRoomByType(String type){
        List<ConsultingRoom> consultingRooms = consultingRoomRepository.findAllByType(type);
        return consultingRooms.stream().map(this::mapToResponse).toList();
    }

}
