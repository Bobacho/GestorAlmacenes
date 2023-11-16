package com.example.gestoralmacenes.models.almacen;

import java.util.List;

public class BloqueEstanteria {
    Long Id;
    Float Largo;
    Float Altura;
    Float Profundidad;
    Integer PesoMaximo;
    Integer PesoActual;
    Integer Fila;
    Integer Columna;
    Integer Cara;
    List<Contenedor> contenedores;

    public BloqueEstanteria(Long id, Float largo, Float altura, Float profundidad, Integer pesoMaximo, Integer pesoActual, Integer fila, Integer columna, Integer cara, List<Contenedor> contenedores) {
        Id = id;
        Largo = largo;
        Altura = altura;
        Profundidad = profundidad;
        PesoMaximo = pesoMaximo;
        PesoActual = pesoActual;
        Fila = fila;
        Columna = columna;
        Cara = cara;
        this.contenedores = contenedores;
    }

    @Override
    public String toString() {
        return "BloqueEstanteria{" +
                "Largo=" + Largo +
                ", Altura=" + Altura +
                ", Profundidad=" + Profundidad +
                ", PesoMaximo=" + PesoMaximo +
                ", PesoActual=" + PesoActual +
                ", Fila=" + Fila +
                ", Columna=" + Columna +
                ", Cara=" + Cara +
                ", contenedores=" + contenedores +
                '}';
    }

    public List<Contenedor> getContenedores() {
        return contenedores;
    }

    public void setContenedores(List<Contenedor> contenedores) {
        this.contenedores = contenedores;
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public Integer getPesoMaximo() {
        return PesoMaximo;
    }

    public void setPesoMaximo(Integer pesoMaximo) {
        PesoMaximo = pesoMaximo;
    }

    public Integer getPesoActual() {
        return PesoActual;
    }

    public void setPesoActual(Integer pesoActual) {
        PesoActual = pesoActual;
    }

    public Integer getFila() {
        return Fila;
    }

    public void setFila(Integer fila) {
        Fila = fila;
    }

    public Integer getColumna() {
        return Columna;
    }

    public void setColumna(Integer columna) {
        Columna = columna;
    }

    public Integer getCara() {
        return Cara;
    }

    public void setCara(Integer cara) {
        Cara = cara;
    }
}
