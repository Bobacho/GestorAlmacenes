package com.example.gestoralmacenes.models.personas;

public class Cliente {
    private Long Id;
    private String Nombre;
    private String Telefono;

    public Cliente(Long id, String nombre, String telefono) {
        Id = id;
        Nombre = nombre;
        Telefono = telefono;
    }

    public Long getId() {
        return Id;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "\nNombre='" + Nombre + '\'' +
                "\nTelefono='" + Telefono + '\'' +
                "}\n";
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

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }
}
