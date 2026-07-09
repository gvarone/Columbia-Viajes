
package com.project.columbiaviajes.modelo;

import java.io.Serializable;


public class Turista implements Serializable {
    private int codigo;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String email;
    
    public Turista(int codigo, String nombre,String apellido, String direccion, String telefono, String email){
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }
    
    public int getCodigo() {
        return codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}
