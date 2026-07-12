
package Modelos;


public class Turista{
    private final int codigo;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String email;
    private boolean eliminado = false;
    
    public Turista(int codigo, String nombre,String apellido, String direccion, 
            String telefono, String email){
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }
    
    public boolean isEliminado(){
        return eliminado;
    }
    
    public void eliminar(){
        this.eliminado = true;
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

    public String getApellido() {
        return apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
