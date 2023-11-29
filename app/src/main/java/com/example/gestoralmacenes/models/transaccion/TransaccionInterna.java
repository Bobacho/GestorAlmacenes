package com.example.gestoralmacenes.models.transaccion;

import com.example.gestoralmacenes.models.almacen.Almacen;
import com.example.gestoralmacenes.models.personas.Empleado;

import java.time.LocalDate;
import java.util.List;

public class TransaccionInterna extends Transaccion{
    List<TransaccionInternaUnitaria> transacciones;
    Almacen Almacen;
    Empleado Encargado;

    public TransaccionInterna(Long id, LocalDate fechaInicio, LocalDate fechaFin, List<TransaccionInternaUnitaria> transacciones, Almacen idAlmacen, Empleado encargado) {
        super(id, fechaInicio, fechaFin);
        this.transacciones = transacciones;
        Almacen = idAlmacen;
        Encargado = encargado;
        TipoTransaccion = "Interna";
    }

    @Override
    public String toString() {
        return "TransaccionInterna{" +
                "\nFechaInicio=" + FechaInicio +
                "\nFechaFin=" + FechaFin +
                "\ntransacciones=" + transacciones +
                "\nAlmacen=" + Almacen +
                "\nEncargado=" + Encargado +
                "\nTipoTransaccion='" + TipoTransaccion + '\'' +
                "\n}";
    }

    public Almacen getAlmacen() {
        return Almacen;
    }

    public void setAlmacen(Almacen almacen) {
        Almacen = almacen;
    }

    public Empleado getEncargado() {
        return Encargado;
    }

    public void setEncargado(Empleado encargado) {
        Encargado = encargado;
    }

    public List<TransaccionInternaUnitaria> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<TransaccionInternaUnitaria> transacciones) {
        this.transacciones = transacciones;
    }
}
