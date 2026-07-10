package com.project.columbiaviajes.modelo;

import com.project.columbiaviajes.enums.Rol;
import java.io.Serializable;

public abstract class Usuario implements Serializable {

    private int codigo;
    private String nombre;
    private String apellido;
    private String contrasenia;
    private Rol rol;

    public Usuario(int codigo, String nombre, String apellido, String contrasenia, Rol rol) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public boolean validarContrasenia(String intento) {
        return this.contrasenia.equals(intento);
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

    public Rol getRol() {
        return rol;
    }

    public String getApellido() {
        return apellido;
    }

}
