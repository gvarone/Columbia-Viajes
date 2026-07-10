package Modelos;

import Enums.Rol;

public class Administrador extends Usuario {

    public Administrador(int codigo, String nombre, String apellido, String contrasenia) {
        super(codigo, nombre, apellido, contrasenia, Rol.ADMIN);
    }
}
