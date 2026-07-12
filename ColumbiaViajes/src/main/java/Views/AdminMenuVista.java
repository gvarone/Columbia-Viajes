
package Views;

import java.util.Scanner;

public class AdminMenuVista extends MenuVistaBase {    
    public AdminMenuVista() {
        this.entrada = new Scanner(System.in);
    }
    
    @Override
    public void mostrarMenu() {
        System.out.println("---Menu Administradores---");
        System.out.println("0- Salir");
        System.out.println("1- Agregar una Sucursal");
        System.out.println("2- Agregar un Hotel");
        System.out.println("3- Agregar un Vuelo");
        System.out.println("4- Agregar un Usuario");
        System.out.println("5- Listar sucursales");
        System.out.println("6- Listar hoteles");
        System.out.println("7- Listar vuelos");
        System.out.println("8- Listar clientes/turistas");
        System.out.println("9- Modificar sucursales");
        System.out.println("10- Modificar hoteles");
        System.out.println("11- Modificar vuelos");
        System.out.println("12- Modificar clientes/turistas");
        System.out.println("13- Eliminar sucursales");
        System.out.println("14- Eliminar hoteles");
        System.out.println("15- Eliminar vuelos");
        System.out.println("16- Eliminar clientes/turistas");
        System.out.println("17- Asignar un rol a un usuario");
    } 
}
