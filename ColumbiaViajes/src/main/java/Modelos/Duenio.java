
package Modelos;

import Enums.Rol;

public class Duenio extends Usuario {
    public Duenio(int codigo, String nombre, String apellido, String contrasenia,
            String username) {

        super(codigo, nombre, apellido, contrasenia, Rol.DUENIO, username);
    }
}
