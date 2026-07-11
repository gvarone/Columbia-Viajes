
package Views;

import java.util.Scanner;

public class DuenioMenuVista implements MenuVista {
    private Scanner entrada;
    
    public DuenioMenuVista() {
        this.entrada = new Scanner(System.in);
    }
    
    @Override
    public void mostrarMenu() {
        System.out.println("---Menu Dueños---");
        System.out.println("1- Listar vendedores");
    }

    @Override
    public int leerOpcion() {
        int opt = 0;
        boolean esValido = false;
        
        do{
            try {
                opt = Integer.parseInt(entrada.nextLine().trim());
                esValido = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debe ingresar un número entero.");
            }
        } while(!esValido);
        
        return opt;
    }
    
}
