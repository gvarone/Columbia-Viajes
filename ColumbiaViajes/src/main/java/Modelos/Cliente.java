package Modelos;

import Enums.Rol;

public class Cliente extends Usuario {

    private final int codTurista;
    
    private boolean eliminado = false;

    public Cliente(int codigo, String nombre, String apellido, String contrasenia,
            int codTurista) {

        super(codigo, nombre, apellido, contrasenia, Rol.CLIENTE);
        this.codTurista = codTurista;
    }

    public int getCodTurista() {
        return codTurista;
    }
    
    public boolean isEliminado(){
        return eliminado;
    }
    
    public void eliminar(){
        this.eliminado = true;
    }
}
