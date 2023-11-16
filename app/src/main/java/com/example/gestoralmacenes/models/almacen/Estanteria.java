package com.example.gestoralmacenes.models.almacen;

import com.example.gestoralmacenes.models.almacen.BloqueEstanteria;

import java.util.List;

public class Estanteria {
    private Long Id;
    private String NroLote;
    private String categoria;
    private List<BloqueEstanteria> bloques;

    public Estanteria(Long id, String nroLote, String categoria, List<BloqueEstanteria> bloques) {
        Id = id;
        NroLote = nroLote;
        this.categoria = categoria;
        this.bloques = bloques;
    }

    @Override
    public String toString() {
        return "Estanteria{" +
                "NroLote='" + NroLote + '\'' +
                ", categoria='" + categoria + '\'' +
                ", bloques=" + bloques +
                '}';
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNroLote() {
        return NroLote;
    }

    public void setNroLote(String nroLote) {
        NroLote = nroLote;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<BloqueEstanteria> getBloques() {
        return bloques;
    }

    public void setBloques(List<BloqueEstanteria> bloques) {
        this.bloques = bloques;
    }
}
