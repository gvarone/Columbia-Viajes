
package com.project.columbiaviajes.modelo;

import com.project.columbiaviajes.enums.Rol;

public class Duenio extends Usuario {
    public Duenio(int codigo, String nombre, String apellido, String contrasenia) {

        super(codigo, nombre, apellido, contrasenia, Rol.DUENIO);
    }
}
