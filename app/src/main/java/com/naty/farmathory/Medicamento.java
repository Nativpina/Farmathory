package com.naty.farmathory;

import java.io.Serializable;

public class Medicamento implements Serializable {
   private String nombre;
    private String cantidad;
    private String hora;
    private String fecha;
    private String recordatorio;

    public Medicamento(String nombre, String cantidad, String hora, String fecha, String recordatorio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.hora = hora;
        this.fecha = fecha;
        this.recordatorio = recordatorio;

    }

    @Override
    public String toString() {
        return "Medicamento{" +
                "nombre='" + nombre + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getRecordatorio() {
        return recordatorio;
    }

    public void setRecordatorio(String recordatorio) {
        this.recordatorio = recordatorio;
    }
}
