package com.example.gestoralmacenes.models.almacen;

import java.util.List;

public class Contenedor {
    Long Id;
    Long CapacidadMaxima;
    Long CapacidadActual;
    Float Largo;
    Float Altura;
    Float Profundidad;
    Float PesoMaximo;
    List<Producto> productos;
    List<BloqueEstanteria> bloqueEstanteria;

    public Contenedor(Long id, Long capacidadMaxima, Long capacidadActual, Float largo, Float altura, Float profundidad, Float pesoMaximo, List<Producto> productos, List<BloqueEstanteria> bloqueEstanteria) {
        Id = id;
        CapacidadMaxima = capacidadMaxima;
        CapacidadActual = capacidadActual;
        Largo = largo;
        Altura = altura;
        Profundidad = profundidad;
        PesoMaximo = pesoMaximo;
        this.productos = productos;
        this.bloqueEstanteria = bloqueEstanteria;
    }

    @Override
    public String toString() {
        return "Contenedor{" +
                "\nCapacidadMaxima=" + CapacidadMaxima +
                "\nCapacidadActual=" + CapacidadActual +
                "\nLargo=" + Largo +
                "\nAltura=" + Altura +
                "\nProfundidad=" + Profundidad +
                "\nPesoMaximo=" + PesoMaximo +
                "\nproductos=" + productos +
                "\nbloqueEstanteria=" + bloqueEstanteria +
                "\n}";
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getCapacidadMaxima() {
        return CapacidadMaxima;
    }

    public void setCapacidadMaxima(Long capacidadMaxima) {
        CapacidadMaxima = capacidadMaxima;
    }

    public Long getCapacidadActual() {
        return CapacidadActual;
    }

    public void setCapacidadActual(Long capacidadActual) {
        CapacidadActual = capacidadActual;
    }

    public Float getLargo() {
        return Largo;
    }

    public void setLargo(Float largo) {
        Largo = largo;
    }

    public Float getAltura() {
        return Altura;
    }

    public void setAltura(Float altura) {
        Altura = altura;
    }

    public Float getProfundidad() {
        return Profundidad;
    }

    public void setProfundidad(Float profundidad) {
        Profundidad = profundidad;
    }

    public Float getPesoMaximo() {
        return PesoMaximo;
    }

    public void setPesoMaximo(Float pesoMaximo) {
        PesoMaximo = pesoMaximo;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<BloqueEstanteria> getBloqueEstanteria() {
        return bloqueEstanteria;
    }

    public void setBloqueEstanteria(List<BloqueEstanteria> bloqueEstanteria) {
        this.bloqueEstanteria = bloqueEstanteria;
    }
}
