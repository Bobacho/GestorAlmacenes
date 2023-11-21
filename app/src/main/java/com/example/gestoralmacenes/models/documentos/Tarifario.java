package com.example.gestoralmacenes.models.documentos;

import java.time.LocalDate;

public class Tarifario {
    private Long Id;
    private Float PrecioUnitario;
    private Integer Cantidad;
    private Float Impuestos;
    private Float Descuento;
    private LocalDate FechaVencimiento;

    public Tarifario(Long id, Float precioUnitario, Integer cantidad, Float impuestos, Float descuento, LocalDate fechaVencimiento) {
        Id = id;
        PrecioUnitario = precioUnitario;
        Cantidad = cantidad;
        Impuestos = impuestos;
        Descuento = descuento;
        FechaVencimiento = fechaVencimiento;
    }

    @Override
    public String toString() {
        return "Tarifario{" +
                "PrecioUnitario=" + PrecioUnitario +
                ", Cantidad=" + Cantidad +
                ", Impuestos=" + Impuestos +
                ", Descuento=" + Descuento +
                ", FechaVencimiento=" + FechaVencimiento +
                '}';
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Float getPrecioUnitario() {
        return PrecioUnitario;
    }

    public void setPrecioUnitario(Float precioUnitario) {
        PrecioUnitario = precioUnitario;
    }

    public Integer getCantidad() {
        return Cantidad;
    }

    public void setCantidad(Integer cantidad) {
        Cantidad = cantidad;
    }

    public Float getImpuestos() {
        return Impuestos;
    }

    public void setImpuestos(Float impuestos) {
        Impuestos = impuestos;
    }

    public Float getDescuento() {
        return Descuento;
    }

    public void setDescuento(Float descuento) {
        Descuento = descuento;
    }

    public LocalDate getFechaVencimiento() {
        return FechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        FechaVencimiento = fechaVencimiento;
    }
}
