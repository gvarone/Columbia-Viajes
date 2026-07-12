
package Enums;

public enum Rol {
    DUENIO("Dueño"),
    ADMIN("Admin"),
    VENDEDOR("Vendedor"),
    CLIENTE("Cliente");
    
    private final String descripcion;
    
    Rol(String desc){
        this.descripcion = desc;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    
}
