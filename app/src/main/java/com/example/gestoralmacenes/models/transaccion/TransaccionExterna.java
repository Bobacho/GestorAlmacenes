package com.example.gestoralmacenes.models.transaccion;

import com.example.gestoralmacenes.models.documentos.Documento;
import com.example.gestoralmacenes.models.documentos.RegistrosContables;

import java.time.LocalDate;
import java.util.List;

public class TransaccionExterna extends Transaccion{
    List<TransaccionExternaUnitaria> transacciones;
    Documento documento;
    RegistrosContables registrosContable;

    public TransaccionExterna(Long id, LocalDate fechaInicio, LocalDate fechaFin, List<TransaccionExternaUnitaria> transacciones, Documento documento, RegistrosContables registrosContable) {
        super(id, fechaInicio, fechaFin);
        this.transacciones = transacciones;
        this.documento = documento;
        this.registrosContable = registrosContable;
        TipoTransaccion="Externa";
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public RegistrosContables getRegistrosContable() {
        return registrosContable;
    }

    public void setRegistrosContable(RegistrosContables registrosContable) {
        this.registrosContable = registrosContable;
    }

    public List<TransaccionExternaUnitaria> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<TransaccionExternaUnitaria> transacciones) {
        this.transacciones = transacciones;
    }

    @Override
    public String toString() {
        return "TransaccionExterna{" +
                "transacciones=" + transacciones +
                ", documento=" + documento +
                ", registrosContable=" + registrosContable +
                ", FechaInicio=" + FechaInicio +
                ", FechaFin=" + FechaFin +
                ", TipoTransaccion='" + TipoTransaccion + '\'' +
                '}';
    }
}
