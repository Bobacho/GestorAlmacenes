package com.example.gestoralmacenes.models.personas;

import com.example.gestoralmacenes.models.transaccion.Transaccion;

import java.util.List;

public class Empleado {
    private Long Id;
    private String Nombre;
    private String Rango;
    private String DescripcionResponsabilidad;
    private Usuario usuario;
    private String DNI;
    private String NivelEstudio;
    private String Telefono;
    private String Correo;
    private List<Transaccion> transacciones;

    public Empleado(Long id, String nombre, String rango, String descripcionResponsabilidad, Usuario usuario, String DNI, String nivelEstudio, String telefono, String correo, List<Transaccion> transacciones) {
        Id = id;
        Nombre = nombre;
        Rango = rango;
        DescripcionResponsabilidad = descripcionResponsabilidad;
        this.usuario = usuario;
        this.DNI = DNI;
        NivelEstudio = nivelEstudio;
        Telefono = telefono;
        Correo = correo;
        this.transacciones = transacciones;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "Nombre='" + Nombre + '\'' +
                ", Rango='" + Rango + '\'' +
                ", DescripcionResponsabilidad='" + DescripcionResponsabilidad + '\'' +
                ", usuario=" + usuario +
                ", DNI='" + DNI + '\'' +
                ", NivelEstudio='" + NivelEstudio + '\'' +
                ", Telefono='" + Telefono + '\'' +
                ", Correo='" + Correo + '\'' +
                ", transacciones=" + transacciones +
                '}';
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getRango() {
        return Rango;
    }

    public void setRango(String rango) {
        Rango = rango;
    }

    public String getDescripcionResponsabilidad() {
        return DescripcionResponsabilidad;
    }

    public void setDescripcionResponsabilidad(String descripcionResponsabilidad) {
        DescripcionResponsabilidad = descripcionResponsabilidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNivelEstudio() {
        return NivelEstudio;
    }

    public void setNivelEstudio(String nivelEstudio) {
        NivelEstudio = nivelEstudio;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }
}
