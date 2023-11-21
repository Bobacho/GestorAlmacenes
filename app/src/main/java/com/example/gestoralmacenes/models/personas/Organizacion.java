package com.example.gestoralmacenes.models.personas;

public class Organizacion {
    private Long Id;
    private String Nombre;
    private String Ruuc;
    private String TipoOrganizacion;
    private Integer Tamaño;
    private String Sector;

    public Organizacion(Long id, String nombre, String ruuc, String tipoOrganizacion, Integer tamaño, String sector) {
        Id = id;
        Nombre = nombre;
        Ruuc = ruuc;
        TipoOrganizacion = tipoOrganizacion;
        Tamaño = tamaño;
        Sector = sector;
    }

    @Override
    public String toString() {
        return "Organizacion{" +
                "Nombre='" + Nombre + '\'' +
                ", Ruuc='" + Ruuc + '\'' +
                ", TipoOrganizacion='" + TipoOrganizacion + '\'' +
                ", Tamaño=" + Tamaño +
                ", Sector='" + Sector + '\'' +
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

    public String getRuuc() {
        return Ruuc;
    }

    public void setRuuc(String ruuc) {
        Ruuc = ruuc;
    }

    public String getTipoOrganizacion() {
        return TipoOrganizacion;
    }

    public void setTipoOrganizacion(String tipoOrganizacion) {
        TipoOrganizacion = tipoOrganizacion;
    }

    public Integer getTamaño() {
        return Tamaño;
    }

    public void setTamaño(Integer tamaño) {
        Tamaño = tamaño;
    }

    public String getSector() {
        return Sector;
    }

    public void setSector(String sector) {
        Sector = sector;
    }
}
