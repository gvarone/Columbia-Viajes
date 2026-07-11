
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
    
    public static Rol parseRol(String textoBuscado) {
        // Recorremos todos los valores posibles de este Enum
        for (Rol rol : Rol.values()) {
            
            // Usamos equalsIgnoreCase para que no importe si hay mayúsculas o minúsculas
            if (rol.descripcion.equalsIgnoreCase(textoBuscado.trim())) {
                return rol; // ¡Lo encontramos!
            }
        }
        
        // Si termina el bucle y no lo encontró, lanzamos un error (o podrías devolver null)
        throw new IllegalArgumentException("No se encontró ningún Rol para el texto: " + textoBuscado);
    }
}
