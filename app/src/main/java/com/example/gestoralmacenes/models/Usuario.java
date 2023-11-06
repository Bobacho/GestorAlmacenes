package com.example.gestoralmacenes.models;

public class Usuario {
    private String nombre;
    private String contraseña;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;
    private Long idEmpleado;

    public Usuario(Long id,String nombre, String contraseña, Long idEmpleado) {
        this.id=id;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.idEmpleado = idEmpleado;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", id=" + id +
                ", idEmpleado=" + idEmpleado +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
}
