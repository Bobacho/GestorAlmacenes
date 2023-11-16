package com.example.gestoralmacenes.models.transaccion;

import java.time.LocalDate;

public class Transaccion implements Comparable<Transaccion>{
    Long Id;
    LocalDate FechaInicio;
    LocalDate FechaFin;

    public Transaccion(Long id, LocalDate fechaInicio, LocalDate fechaFin) {
        Id = id;
        FechaInicio = fechaInicio;
        FechaFin = fechaFin;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public LocalDate getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        FechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        FechaFin = fechaFin;
    }

    @Override
    public int compareTo(Transaccion o) {
        return this.FechaFin.compareTo(o.FechaFin);
    }
}
