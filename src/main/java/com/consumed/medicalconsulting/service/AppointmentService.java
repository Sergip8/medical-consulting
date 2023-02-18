package com.consumed.medicalconsulting.service;

import com.consumed.medicalconsulting.dto.AppointmentRequest;
import com.consumed.medicalconsulting.dto.AppointmentResponse;
import com.consumed.medicalconsulting.exception.SpringRedditException;
import com.consumed.medicalconsulting.models.ConsultingRoom;
import com.consumed.medicalconsulting.models.Doctor;
import com.consumed.medicalconsulting.models.Enums.AppointmentState;
import com.consumed.medicalconsulting.models.Enums.Months;
import com.consumed.medicalconsulting.models.MedicalAppointments;
import com.consumed.medicalconsulting.models.Patient;
import com.consumed.medicalconsulting.repository.AppointmentRepository;
import com.consumed.medicalconsulting.repository.ConsultingRoomRepository;
import com.consumed.medicalconsulting.repository.DoctorRepository;
import com.consumed.medicalconsulting.repository.PatientRepository;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ConsultingRoomRepository consultingRoomRepository;
    private final AppointmentRepository appointmentRepository;
    private String res;
    @Transactional
    public String createAppointment(AppointmentRequest appointmentRequest){
        MedicalAppointments appointment = mapToAppointment(appointmentRequest);

        appointment.getDoctor().getMedicalAppointmentsList().add(appointment);
        appointment.getPatient().getMedicalAppointmentsList().add(appointment);
        appointment.getMedicalConsulting().getMedicalAppointmentsList().add(appointment);
        appointmentRepository.save(appointment);
        //doctorRepository.save(appointment.getDoctor());
        return this.res;

    }
    private MedicalAppointments mapToAppointment(AppointmentRequest appointmentRequest){
        Doctor doctor = doctorRepository.findById(appointmentRequest.getDoctorId())
                .orElseThrow(() -> new SpringRedditException("No encontro al dotor"));
        Patient patient = patientRepository.findByIdentityNumber(appointmentRequest.getPatientCC())
                .orElseThrow(() -> new SpringRedditException("No encontro al pendejo"));
        ConsultingRoom consultingRoom = consultingRoomRepository.findById(appointmentRequest.getMedicalConsultingId())
                .orElseThrow(() -> new SpringRedditException("No encontro el consultorio"));


        this.res = "La cita fue agendada para el dia "
                + appointmentRequest.getAppointmentDate().getDayOfMonth()+ " de "
                + Months.values()[appointmentRequest.getAppointmentDate().getMonthValue()] + " a las "
                + appointmentRequest.getAppointmentDate().format(DateTimeFormatter.ofPattern("hh:mm a"))
                + " con el doctor " + doctor.getFirstname() + " "
                + doctor.getLastname() + " en el centro medico "
                + appointmentRequest.getMedicalCenterName() + " con direccion "
                + appointmentRequest.getAddress()
                ;

        return MedicalAppointments.builder()
                .appointmentDate(appointmentRequest.getAppointmentDate())
                .description(appointmentRequest.getDescription())
                .createdDate(Instant.now())
                .type(appointmentRequest.getType())
                .patient(patient)
                .doctor(doctor)
                .medicalConsulting(consultingRoom)
                .state(AppointmentState.Asignada)
                .response(res)
                .build();
    }


    public List<AppointmentResponse> getAllPatientAppointments(String cc) {
        List<MedicalAppointments> appointments = appointmentRepository.findAllByPatientIdentityNumber(cc)
                .orElseThrow(() -> new SpringRedditException("No hay citas agendadas"));
        return appointments.stream().map(this::mapToAppointmentResponse).toList();
    }

    private AppointmentResponse mapToAppointmentResponse(MedicalAppointments appointments) {

        return AppointmentResponse.builder()
                .id(appointments.getId())
                .type(appointments.getType())
                .description(appointments.getDescription())
                .createdDate(appointments.getCreatedDate())
                .appointmentDate(appointments.getAppointmentDate())
                .response(appointments.getResponse())
                .appointmentDate(appointments.getAppointmentDate())
                .build();
    }
}
