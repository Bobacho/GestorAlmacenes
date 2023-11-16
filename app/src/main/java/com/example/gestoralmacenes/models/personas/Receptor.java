package com.example.gestoralmacenes.models.personas;

import com.example.gestoralmacenes.models.documentos.Documento;

import java.util.List;

public class Receptor {
    private Long Id;
    private Cliente client;
    private Empleado empleado;
    private Proveedor proveedor;
    private String TipoReceptor;
    private List<Documento> Documentos;
    public Receptor(Long id, Cliente client, Empleado empleado, Proveedor proveedor, String tipoReceptor, List<Documento> documentos) {
        Id = id;
        this.client = client;
        this.empleado = empleado;
        this.proveedor = proveedor;
        TipoReceptor = tipoReceptor;
        Documentos = documentos;
    }

    public String getTipoReceptor() {
        return TipoReceptor;
    }

    public void setTipoReceptor(String tipoReceptor) {
        TipoReceptor = tipoReceptor;
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
        return TipoReceptor;
    }

    public void setTipoEmisor(String tipoReceptor) {
        TipoReceptor = tipoReceptor;
    }
}
