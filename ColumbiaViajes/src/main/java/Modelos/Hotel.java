
package Modelos;


public class Hotel{
    private final int codigo;
    private String nombre;
    private String direccion;
    private String ciudad;
    private String telefono;
    private boolean eliminado = false;
    private int plazasDisponibles;
    private final int plazasTotales;

    public Hotel(int codigo, String nombre, int plazas_totales) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.plazasTotales = plazas_totales;
    }

    public Hotel(int codigo, String nombre, String direccion, String ciudad, String telefono,
            int plazasDisponibles, int plazasTotales) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.plazasDisponibles = plazasDisponibles;
        this.plazasTotales = plazasTotales;
    }    
    
    public Hotel(int codigo, String nombre, String direccion, String ciudad, String telefono, boolean eliminado,
            int plazasDisponibles, int plazasTotales) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.eliminado = eliminado;
        this.plazasDisponibles = plazasDisponibles;
        this.plazasTotales = plazasTotales;
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

    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getPlazasDisponibles() {
        return plazasDisponibles;
    }

    public final int getPlazasTotales() {
        return plazasTotales;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void reservarPlaza() {
        this.plazasDisponibles -= 1;
    }
    
    public void liberarPlaza() {
        this.plazasDisponibles += 1;
    }
}
