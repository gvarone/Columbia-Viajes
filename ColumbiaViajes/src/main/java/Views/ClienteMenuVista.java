
package Views;

import java.util.Scanner;

public class ClienteMenuVista extends MenuVistaBase {
    
    public ClienteMenuVista() {
        this.entrada = new Scanner(System.in);
    }

    @Override
    public void mostrarMenu() {
        System.out.println("---Menu Clientes---");
        System.out.println("0- Salir");
        System.out.println("1- Ver hoteles disponibles");
        System.out.println("2- Ver vuelos disponibles");
        System.out.println("3- Ver paquetes en propiedad");
    }
    
   
}
