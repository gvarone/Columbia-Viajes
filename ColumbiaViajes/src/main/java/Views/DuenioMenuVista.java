
package Views;

import java.util.Scanner;

public class DuenioMenuVista extends MenuVistaBase {    
    public DuenioMenuVista() {
        this.entrada = new Scanner(System.in);
    }
    
    @Override
    public void mostrarMenu() {
        System.out.println("---Menu Dueños---");
        System.out.println("0- Salir");
        System.out.println("1- Listar vendedores");
    }
    
}
