
package Views;

import java.util.Scanner;

public class VendedorMenuVista extends MenuVistaBase {
    private Scanner entrada;
    
    public VendedorMenuVista() {
        this.entrada = new Scanner(System.in);
    }
    
    @Override
    public void mostrarMenu() {
        System.out.println("---Menu Vendedores---");
        System.out.println("1- Listar clientes actuales");
        System.out.println("2- Buscar un cliente por nombre");
        System.out.println("3- Asignar un paquete a un cliente");
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
