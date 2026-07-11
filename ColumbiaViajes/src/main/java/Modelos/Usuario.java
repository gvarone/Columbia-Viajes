package Modelos;

import Enums.Rol;
import java.io.Serializable;

public abstract class Usuario implements Serializable {

    private final int codigo;
    private String nombre;
    private String apellido;
    private String username;
    private String contrasenia;
    private Rol rol;
    private boolean eliminado = false;

    public Usuario(int codigo, String nombre, String apellido, String contrasenia, Rol rol, String username) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.username = username;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public boolean isEliminado(){
        return eliminado;
    }
    
    public void eliminar(){
        this.eliminado = true;
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
