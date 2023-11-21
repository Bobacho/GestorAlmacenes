package com.example.gestoralmacenes.models.personas;

public class Proveedor {
    private Long Id;
    private String Nombre;
    private String TelefonoContacto;
    private String Direccion;
    private String RUUC;
    private String GiroProveedor;
    private String TelefonoProveedor;
    private String Contacto;

    public Proveedor(Long id, String nombre, String telefonoContacto, String direccion, String RUUC, String giroProveedor, String telefonoProveedor, String contacto) {
        Id = id;
        Nombre = nombre;
        TelefonoContacto = telefonoContacto;
        Direccion = direccion;
        this.RUUC = RUUC;
        GiroProveedor = giroProveedor;
        TelefonoProveedor = telefonoProveedor;
        Contacto = contacto;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "Nombre='" + Nombre + '\'' +
                ", TelefonoContacto='" + TelefonoContacto + '\'' +
                ", Direccion='" + Direccion + '\'' +
                ", RUUC='" + RUUC + '\'' +
                ", GiroProveedor='" + GiroProveedor + '\'' +
                ", TelefonoProveedor='" + TelefonoProveedor + '\'' +
                ", Contacto='" + Contacto + '\'' +
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

    public String getTelefonoContacto() {
        return TelefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        TelefonoContacto = telefonoContacto;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getRUUC() {
        return RUUC;
    }

    public void setRUUC(String RUUC) {
        this.RUUC = RUUC;
    }

    public String getGiroProveedor() {
        return GiroProveedor;
    }

    public void setGiroProveedor(String giroProveedor) {
        GiroProveedor = giroProveedor;
    }

    public String getTelefonoProveedor() {
        return TelefonoProveedor;
    }

    public void setTelefonoProveedor(String telefonoProveedor) {
        TelefonoProveedor = telefonoProveedor;
    }

    public String getContacto() {
        return Contacto;
    }

    public void setContacto(String contacto) {
        Contacto = contacto;
    }
}
