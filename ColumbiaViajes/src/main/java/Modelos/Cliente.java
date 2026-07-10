package Modelos;

import Enums.Rol;

public class Cliente extends Usuario {

    private int codTurista;

    public Cliente(int codigo, String nombre, String apellido, String contrasenia,
            int codTurista) {

        super(codigo, nombre, apellido, contrasenia, Rol.CLIENTE);
        this.codTurista = codTurista;
    }

    public int getCodTurista() {
        return codTurista;
    }
}
