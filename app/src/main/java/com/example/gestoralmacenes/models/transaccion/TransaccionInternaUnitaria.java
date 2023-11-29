package com.example.gestoralmacenes.models.transaccion;

import com.example.gestoralmacenes.models.almacen.BloqueEstanteria;
import com.example.gestoralmacenes.models.almacen.Estanteria;

public class TransaccionInternaUnitaria {
    Long Id;
    Estanteria Origen;
    Estanteria Destino;
    BloqueEstanteria BloqueOrigen;
    BloqueEstanteria BloqueDestino;
    com.example.gestoralmacenes.models.almacen.Contenedor Contenedor;

    public TransaccionInternaUnitaria(Long id, Estanteria origen, Estanteria destino, BloqueEstanteria bloqueOrigen, BloqueEstanteria bloqueDestino, com.example.gestoralmacenes.models.almacen.Contenedor contenedor) {
        Id = id;
        Origen = origen;
        Destino = destino;
        BloqueOrigen = bloqueOrigen;
        BloqueDestino = bloqueDestino;
        Contenedor = contenedor;
    }

    @Override
    public String toString() {
        return "DETALLES{" +
                "\nOrigen=" + Origen +
                "\nDestino=" + Destino +
                "\nBloqueOrigen=" + BloqueOrigen +
                "\nBloqueDestino=" + BloqueDestino +
                "\nContenedor=" + Contenedor +
                "\n}";
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Estanteria getOrigen() {
        return Origen;
    }

    public void setOrigen(Estanteria origen) {
        Origen = origen;
    }

    public Estanteria getDestino() {
        return Destino;
    }

    public void setDestino(Estanteria destino) {
        Destino = destino;
    }

    public BloqueEstanteria getBloqueOrigen() {
        return BloqueOrigen;
    }

    public void setBloqueOrigen(BloqueEstanteria bloqueOrigen) {
        BloqueOrigen = bloqueOrigen;
    }

    public BloqueEstanteria getBloqueDestino() {
        return BloqueDestino;
    }

    public void setBloqueDestino(BloqueEstanteria bloqueDestino) {
        BloqueDestino = bloqueDestino;
    }

    public com.example.gestoralmacenes.models.almacen.Contenedor getContenedor() {
        return Contenedor;
    }

    public void setContenedor(com.example.gestoralmacenes.models.almacen.Contenedor contenedor) {
        Contenedor = contenedor;
    }
}
