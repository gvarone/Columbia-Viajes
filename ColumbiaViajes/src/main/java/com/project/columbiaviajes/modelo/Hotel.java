
package com.project.columbiaviajes.modelo;

import java.io.Serializable;

public class Hotel implements Serializable{
    private int codigo;
    private String nombre;
    private String direccion;
    private String ciudad;
    private String telefono;
    private int plazas_disponibles;
    private final int plazas_totales;

    public Hotel(int codigo, String nombre, int plazas_totales) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.plazas_totales = plazas_totales;
    }

    public Hotel(int codigo, String nombre, String direccion, String ciudad, String telefono,
            int plazas_disponibles, int plazas_totales) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.plazas_disponibles = plazas_disponibles;
        this.plazas_totales = plazas_totales;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getPlazasDisponibles() {
        return plazas_disponibles;
    }

    public final int getPlazasTotales() {
        return plazas_totales;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void reservarPlaza() {
        this.plazas_disponibles -= 1;
    }
    
    public void liberarPlaza() {
        this.plazas_disponibles += 1;
    }
}
