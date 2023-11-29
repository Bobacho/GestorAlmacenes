package com.example.gestoralmacenes.models.almacen;

import com.example.gestoralmacenes.models.documentos.Tarifario;

import java.util.List;

public class Producto {
    private Long Id;
    private String UnidadMedida;
    private String tipo;
    private String Nombre;
    private String Descripcion;
    private Boolean Garantia;
    private List<Tarifario> tarifarios;
    private Tarifario tarifarioActual;

    public Producto(Long id, String unidadMedida, String tipo, String nombre, String descripcion, Boolean garantia, List<Tarifario> tarifarios, Tarifario tarifarioActual) {
        Id = id;
        UnidadMedida = unidadMedida;
        this.tipo = tipo;
        Nombre = nombre;
        Descripcion = descripcion;
        Garantia = garantia;
        this.tarifarios = tarifarios;
        this.tarifarioActual = tarifarioActual;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "UnidadMedida='" + UnidadMedida + '\'' +
                "\ntipo='" + tipo + '\'' +
                "\n Nombre='" + Nombre + '\'' +
                "\n Descripcion='" + Descripcion + '\'' +
                "\n Garantia=" + Garantia +
                "\n tarifarios=" + tarifarios +
                "\n tarifarioActual=" + tarifarioActual +
                "}\n";
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUnidadMedida() {
        return UnidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        UnidadMedida = unidadMedida;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public Boolean getGarantia() {
        return Garantia;
    }

    public void setGarantia(Boolean garantia) {
        Garantia = garantia;
    }

    public List<Tarifario> getTarifarios() {
        return tarifarios;
    }

    public void setTarifarios(List<Tarifario> tarifarios) {
        this.tarifarios = tarifarios;
    }

    public Tarifario getTarifarioActual() {
        return tarifarioActual;
    }

    public void setTarifarioActual(Tarifario tarifarioActual) {
        this.tarifarioActual = tarifarioActual;
    }
}
