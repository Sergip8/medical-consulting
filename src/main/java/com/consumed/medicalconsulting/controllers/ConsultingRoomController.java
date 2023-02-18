package com.consumed.medicalconsulting.controllers;

import com.consumed.medicalconsulting.dto.ConsultingRoomRequest;
import com.consumed.medicalconsulting.service.ConsultingRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/consulting")
public class ConsultingRoomController {

    private final ConsultingRoomService consultingRoomService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerConsultingRoom(@RequestBody ConsultingRoomRequest consultingRoomRequest){
         consultingRoomService.createConsultingRoom(consultingRoomRequest);
    }
}
