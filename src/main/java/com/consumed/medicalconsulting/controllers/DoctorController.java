package com.consumed.medicalconsulting.controllers;

import com.consumed.medicalconsulting.dto.DoctorRequest;
import com.consumed.medicalconsulting.dto.DoctorResponse;
import com.consumed.medicalconsulting.models.Enums.Specialization;
import com.consumed.medicalconsulting.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/doctor")
@RequiredArgsConstructor
public class DoctorController {
    final DoctorService doctorService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void doctorRegister(@RequestBody DoctorRequest doctorRequest){
        doctorService.register(doctorRequest);
    }
    @GetMapping("/type/{spe}")
    @ResponseStatus(HttpStatus.OK)
    public List<DoctorResponse> getAllDoctorsBySpecialization(@PathVariable Specialization spe){
        return doctorService.getDoctorBySpecialization(spe);
    }
}
