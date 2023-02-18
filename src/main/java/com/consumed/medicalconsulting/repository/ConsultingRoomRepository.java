package com.consumed.medicalconsulting.repository;

import com.consumed.medicalconsulting.models.ConsultingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultingRoomRepository extends JpaRepository<ConsultingRoom, Integer> {
    List<ConsultingRoom> findAllByType(String type);

}
