
package Modelos;

import java.io.Serializable;

public class Sucursal implements Serializable {
    private final int codigo;
    private String direccion;
    private String email;
    private String telefono;
    private boolean eliminado = false;

    
    public Sucursal(int codigo, String direccion, String email, String telefono) {
        this.codigo = codigo;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
    }
    
    public boolean isEliminado(){
        return eliminado;
    }
    
    public void eliminar(){
        this.eliminado = true;
    }
    
    public int getCodigo(){
        return codigo;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
    
}
