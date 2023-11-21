package com.example.gestoralmacenes.models.personas;

import com.example.gestoralmacenes.models.documentos.Documento;

import java.util.List;

public class Emisor {
    private Long Id;
    private Cliente client;
    private Empleado empleado;
    private Proveedor proveedor;
    private String TipoEmisor;
    private List<Documento> Documentos;
    public Emisor(Long id, Cliente client, Empleado empleado, Proveedor proveedor, String tipoEmisor, List<Documento> documentos) {
        Id = id;
        this.client = client;
        this.empleado = empleado;
        this.proveedor = proveedor;
        TipoEmisor = tipoEmisor;
        Documentos = documentos;
    }

    @Override
    public String toString() {
        return "Emisor{" +
                "client=" + client +
                ", empleado=" + empleado +
                ", proveedor=" + proveedor +
                ", TipoEmisor='" + TipoEmisor + '\'' +
                ", Documentos=" + Documentos +
                '}';
    }

    public List<Documento> getDocumentos() {
        return Documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        Documentos = documentos;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Cliente getClient() {
        return client;
    }

    public void setClient(Cliente client) {
        this.client = client;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getTipoEmisor() {
        return TipoEmisor;
    }

    public void setTipoEmisor(String tipoEmisor) {
        TipoEmisor = tipoEmisor;
    }
}
