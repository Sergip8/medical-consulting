package com.consumed.medicalconsulting.models.Enums;

 public enum Months {
    Enero, Febrero, Marzo, Abril, Mayo, Junio, Julio, Agosto, Septiembre, Octubre, Noviembre, Diciembre;
    final int value;

    private Months() {
        this.value = ordinal();
    }
}
