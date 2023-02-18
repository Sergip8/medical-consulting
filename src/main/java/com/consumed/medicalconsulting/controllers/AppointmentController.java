package com.consumed.medicalconsulting.controllers;

import com.consumed.medicalconsulting.dto.AppointmentRequest;
import com.consumed.medicalconsulting.dto.AppointmentResponse;
import com.consumed.medicalconsulting.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/citas")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String registerAppointment(@RequestBody AppointmentRequest appointmentRequest){
        return appointmentService.createAppointment(appointmentRequest);
    }

    @GetMapping("/patient/{cc}")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentResponse> getAppointmentsByPatientCC(@PathVariable String cc){
        return appointmentService.getAllPatientAppointments(cc);
    }
}
