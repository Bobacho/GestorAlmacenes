package com.example.gestoralmacenes.models.transaccion;

import java.time.LocalDate;
import java.util.List;

public class TransaccionInterna extends Transaccion{
    List<TransaccionInternaUnitaria> transacciones;
    public TransaccionInterna(Long id, LocalDate fechaInicio, LocalDate fechaFin, List<TransaccionInternaUnitaria> transacciones) {
        super(id, fechaInicio, fechaFin);
        this.transacciones = transacciones;
    }

    public List<TransaccionInternaUnitaria> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<TransaccionInternaUnitaria> transacciones) {
        this.transacciones = transacciones;
    }
}
