package com.example.gestoralmacenes.models.transaccion;

import com.example.gestoralmacenes.models.almacen.Contenedor;

public class TransaccionExternaUnitaria {
    private Long Id;
    private String Secuencia;
    private Integer Cantidad;
    private String UnidadMedida;
    private Float longitud;
    private Float profundidad;
    private Float altura;
    private Contenedor contenedor;

    public TransaccionExternaUnitaria(Long id, String secuencia, Integer cantidad, String unidadMedida, Float longitud, Float profundidad, Float altura, Contenedor contenedor) {
        Id = id;
        Secuencia = secuencia;
        Cantidad = cantidad;
        UnidadMedida = unidadMedida;
        this.longitud = longitud;
        this.profundidad = profundidad;
        this.altura = altura;
        this.contenedor = contenedor;
    }

    @Override
    public String toString() {
        return "DETALLES{" +
                "\nSecuencia='" + Secuencia + '\'' +
                "\n Cantidad=" + Cantidad +
                "\n UnidadMedida='" + UnidadMedida + '\'' +
                "\n longitud=" + longitud +
                "\n profundidad=" + profundidad +
                "\n altura=" + altura +
                "\n contenedor=" + contenedor +
                "\n}";
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getSecuencia() {
        return Secuencia;
    }

    public void setSecuencia(String secuencia) {
        Secuencia = secuencia;
    }

    public Integer getCantidad() {
        return Cantidad;
    }

    public void setCantidad(Integer cantidad) {
        Cantidad = cantidad;
    }

    public String getUnidadMedida() {
        return UnidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        UnidadMedida = unidadMedida;
    }

    public Float getLongitud() {
        return longitud;
    }

    public void setLongitud(Float longitud) {
        this.longitud = longitud;
    }

    public Float getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(Float profundidad) {
        this.profundidad = profundidad;
    }

    public Float getAltura() {
        return altura;
    }

    public void setAltura(Float altura) {
        this.altura = altura;
    }

    public Contenedor getContenedor() {
        return contenedor;
    }

    public void setContenedor(Contenedor contenedor) {
        this.contenedor = contenedor;
    }
}
