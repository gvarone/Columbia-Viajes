
package Views;

import java.util.Scanner;

public class VendedorMenuVista extends MenuVistaBase {    
    public VendedorMenuVista() {
        this.entrada = new Scanner(System.in);
    }
    
    @Override
    public void mostrarMenu() {
        System.out.println("---Menu Vendedores---");
        System.out.println("0- Salir");
        System.out.println("1- Listar clientes actuales");
        System.out.println("2- Buscar un cliente por nombre");
        System.out.println("3- Asignar un paquete a un cliente");
    }

    
    
}
