package com.consumed.medicalconsulting.helpers;

import com.consumed.medicalconsulting.models.Enums.ConsultingRoomTypes;

public class ConvertEnum {
    public static ConsultingRoomTypes ConsultingTypesFromSpecialization(int esp)
    {
        return switch (esp) {
            case 2, 3, 4 -> ConsultingRoomTypes.Cirugia;
            case 5 -> ConsultingRoomTypes.Cuidados_intensivos;
            case 9, 10 -> ConsultingRoomTypes.Laboratorio_clinico;
            case 11, 20, 22, 28 -> ConsultingRoomTypes.Medicina_Física;
            case 18 -> ConsultingRoomTypes.Oftalmología;
            case 16, 19, 23 -> ConsultingRoomTypes.Pediatria;
            case 17, 24 -> ConsultingRoomTypes.Ginecología;
            case 26, 27 -> ConsultingRoomTypes.Radiologia;
            default -> ConsultingRoomTypes.General;
        };

    }
}
