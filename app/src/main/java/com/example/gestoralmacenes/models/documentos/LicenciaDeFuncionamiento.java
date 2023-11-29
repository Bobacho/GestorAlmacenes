package com.example.gestoralmacenes.models.documentos;

import java.time.LocalDate;

public class LicenciaDeFuncionamiento {
    Long Id;
    String Municipalidad;
    LocalDate FechaDeEmision;

    public LicenciaDeFuncionamiento(Long id, String municipalidad, LocalDate fechaDeEmision) {
        Id = id;
        Municipalidad = municipalidad;
        FechaDeEmision = fechaDeEmision;
    }

    @Override
    public String toString() {
        return "LicenciaDeFuncionamiento{" +
                "\nMunicipalidad='" + Municipalidad + '\'' +
                "\nFechaDeEmision=" + FechaDeEmision +
                "}\n";
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getMunicipalidad() {
        return Municipalidad;
    }

    public void setMunicipalidad(String municipalidad) {
        Municipalidad = municipalidad;
    }

    public LocalDate getFechaDeEmision() {
        return FechaDeEmision;
    }

    public void setFechaDeEmision(LocalDate fechaDeEmision) {
        FechaDeEmision = fechaDeEmision;
    }
}
