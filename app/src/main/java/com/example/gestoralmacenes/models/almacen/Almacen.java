package com.example.gestoralmacenes.models.almacen;

import com.example.gestoralmacenes.models.documentos.LicenciaDeFuncionamiento;
import com.example.gestoralmacenes.models.personas.Empleado;

import java.util.List;

public class Almacen {
    Long Id;
    Integer NumeroEstanterias;
    String Ubicacion;
    LicenciaDeFuncionamiento licencia;

    List<Empleado> empleados;
    List<Estanteria> estanterias;

    public Almacen(Long id, Integer numeroEstanterias, String ubicacion, LicenciaDeFuncionamiento licencia, List<Empleado> empleados, List<Estanteria> estanterias) {
        Id = id;
        NumeroEstanterias = numeroEstanterias;
        Ubicacion = ubicacion;
        this.licencia = licencia;
        this.empleados = empleados;
        this.estanterias = estanterias;
    }

    @Override
    public String toString() {
        return "Almacen{" +
                "NumeroEstanterias=" + NumeroEstanterias +
                ", Ubicacion='" + Ubicacion + '\'' +
                ", licencia=" + licencia +
                ", empleados=" + empleados +
                ", estanterias=" + estanterias +
                '}';
    }

    public List<Estanteria> getEstanterias() {
        return estanterias;
    }

    public void setEstanterias(List<Estanteria> estanterias) {
        this.estanterias = estanterias;
    }


    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getNumeroEstanterias() {
        return NumeroEstanterias;
    }

    public void setNumeroEstanterias(Integer numeroEstanterias) {
        NumeroEstanterias = numeroEstanterias;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        Ubicacion = ubicacion;
    }

    public LicenciaDeFuncionamiento getLicencia() {
        return licencia;
    }

    public void setLicencia(LicenciaDeFuncionamiento licencia) {
        this.licencia = licencia;
    }
}
