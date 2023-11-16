package com.example.gestoralmacenes.models.personas;

import java.time.LocalDate;

public class Usuario {
    private Long Id;
    private String NombreUsuario;
    private String Contraseña;
    private String TipoUsuario;
    private String Nombre;
    private LocalDate FechaRegistro;
    private String TipoActividad;
    private String DireccionIp;

    @Override
    public String toString() {
        return "Usuario{" +
                "Id=" + Id +
                ", NombreUsuario='" + NombreUsuario + '\'' +
                ", Contraseña='" + Contraseña + '\'' +
                ", TipoUsuario='" + TipoUsuario + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", FechaRegistro=" + FechaRegistro +
                ", TipoActividad='" + TipoActividad + '\'' +
                ", DireccionIp='" + DireccionIp + '\'' +
                '}';
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        NombreUsuario = nombreUsuario;
    }

    public String getTipoUsuario() {
        return TipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        TipoUsuario = tipoUsuario;
    }

    public LocalDate getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        FechaRegistro = fechaRegistro;
    }

    public String getTipoActividad() {
        return TipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        TipoActividad = tipoActividad;
    }

    public String getDireccionIp() {
        return DireccionIp;
    }

    public void setDireccionIp(String direccionIp) {
        DireccionIp = direccionIp;
    }

    public Usuario(Long id, String nombreUsuario, String contraseña, String tipoUsuario, String nombre, LocalDate fechaRegistro, String tipoActividad, String direccionIp) {
        Id = id;
        NombreUsuario = nombreUsuario;
        Contraseña = contraseña;
        TipoUsuario = tipoUsuario;
        Nombre = nombre;
        FechaRegistro = fechaRegistro;
        TipoActividad = tipoActividad;
        DireccionIp = direccionIp;
    }
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }


    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        this.Contraseña = contraseña;
    }

}
