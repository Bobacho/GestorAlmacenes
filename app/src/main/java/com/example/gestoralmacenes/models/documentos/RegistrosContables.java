package com.example.gestoralmacenes.models.documentos;

import java.time.LocalDate;
import java.util.List;

public class RegistrosContables {
    private Long Id;
    private String NroOrden;
    private Long Año;
    List<LocalDate> fechasContables;
    List<String> descripciones;
    List<Float> importes;
    List<String> cuentasContables;

    public RegistrosContables(Long id, String nroOrden, Long año, List<LocalDate> fechasContables, List<String> descripciones, List<Float> importes, List<String> cuentasContables) {
        Id = id;
        NroOrden = nroOrden;
        Año = año;
        this.fechasContables = fechasContables;
        this.descripciones = descripciones;
        this.importes = importes;
        this.cuentasContables = cuentasContables;
    }

    @Override
    public String toString() {
        return "RegistrosContables{" +
                "NroOrden='" + NroOrden + '\'' +
                ", Año=" + Año +
                ", fechasContables=" + fechasContables +
                ", descripciones=" + descripciones.toString() +
                ", importes=" + importes.toString() +
                ", cuentasContables=" + cuentasContables.toString() +
                "}\n";
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNroOrden() {
        return NroOrden;
    }

    public void setNroOrden(String nroOrden) {
        NroOrden = nroOrden;
    }

    public Long getAño() {
        return Año;
    }

    public void setAño(Long año) {
        Año = año;
    }

    public List<LocalDate> getFechasContables() {
        return fechasContables;
    }

    public void setFechasContables(List<LocalDate> fechasContables) {
        this.fechasContables = fechasContables;
    }

    public List<String> getDescripciones() {
        return descripciones;
    }

    public void setDescripciones(List<String> descripciones) {
        this.descripciones = descripciones;
    }

    public List<Float> getImportes() {
        return importes;
    }

    public void setImportes(List<Float> importes) {
        this.importes = importes;
    }

    public List<String> getCuentasContables() {
        return cuentasContables;
    }

    public void setCuentasContables(List<String> cuentasContables) {
        this.cuentasContables = cuentasContables;
    }
}
