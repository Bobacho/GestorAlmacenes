package com.example.gestoralmacenes.models.personas;

import com.example.gestoralmacenes.models.almacen.Producto;

import java.util.List;

public class Fabricante {
    Long Id;
    String Nombre;
    String Ubicacion;
    String Contacto;
    List<Producto> productos;

    public Fabricante(Long id, String nombre, String ubicacion, String contacto, List<Producto> productos) {
        Id = id;
        Nombre = nombre;
        Ubicacion = ubicacion;
        Contacto = contacto;
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "Fabricante{" +
                "Nombre='" + Nombre + '\'' +
                ", Ubicacion='" + Ubicacion + '\'' +
                ", Contacto='" + Contacto + '\'' +
                ", productos=" + productos +
                '}';
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

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        Ubicacion = ubicacion;
    }

    public String getContacto() {
        return Contacto;
    }

    public void setContacto(String contacto) {
        Contacto = contacto;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
