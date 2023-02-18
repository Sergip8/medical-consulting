package com.consumed.medicalconsulting.controllers;

import com.consumed.medicalconsulting.dto.PatientRequest;
import com.consumed.medicalconsulting.dto.PatientResponse;
import com.consumed.medicalconsulting.dto.PatientWithAppointments;
import com.consumed.medicalconsulting.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/patient/")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void PatientRegister(@RequestBody PatientRequest patientRequest){
        patientService.register(patientRequest);
    }

    @GetMapping("{patientCC}")
    public PatientResponse getPatientByCC(@PathVariable String patientCC){
        return patientService.getPatientByCC(patientCC);
    }
    @GetMapping
    public PatientResponse getPatientLogged(){
        return patientService.getPatientByCurrentUser();

    }
    /*@PostMapping("/informacion_basica")
    @ResponseStatus(HttpStatus.OK)*/
    @GetMapping("citas/{CC}")
    @ResponseStatus(HttpStatus.OK)
    public PatientWithAppointments getAppointmentOfPatient(@PathVariable String CC){
       return patientService.getPatientAppointments(CC);
    }

}
