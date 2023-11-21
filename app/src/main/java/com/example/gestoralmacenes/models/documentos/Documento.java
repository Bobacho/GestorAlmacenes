package com.example.gestoralmacenes.models.documentos;

import java.time.LocalDate;

public class Documento {
    private Long Id;
    private String NroOrden;
    private LocalDate FechaDeEmision;
    private String Ubicacion;
    private Integer TotalPaquetes;
    private String TipoDocumento;

    public Documento(Long id, String nroOrden, LocalDate fechaDeEmision, String ubicacion, Integer totalPaquetes, String tipoDocumento) {
        Id = id;
        NroOrden = nroOrden;
        FechaDeEmision = fechaDeEmision;
        Ubicacion = ubicacion;
        TotalPaquetes = totalPaquetes;
        TipoDocumento = tipoDocumento;
    }

    @Override
    public String toString() {
        return "Documento{" +
                "NroOrden='" + NroOrden + '\'' +
                ", FechaDeEmision=" + FechaDeEmision +
                ", Ubicacion='" + Ubicacion + '\'' +
                ", TotalPaquetes=" + TotalPaquetes +
                ", TipoDocumento='" + TipoDocumento + '\'' +
                '}';
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

    public LocalDate getFechaDeEmision() {
        return FechaDeEmision;
    }

    public void setFechaDeEmision(LocalDate fechaDeEmision) {
        FechaDeEmision = fechaDeEmision;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        Ubicacion = ubicacion;
    }

    public Integer getTotalPaquetes() {
        return TotalPaquetes;
    }

    public void setTotalPaquetes(Integer totalPaquetes) {
        TotalPaquetes = totalPaquetes;
    }

    public String getTipoDocumento() {
        return TipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        TipoDocumento = tipoDocumento;
    }
}
