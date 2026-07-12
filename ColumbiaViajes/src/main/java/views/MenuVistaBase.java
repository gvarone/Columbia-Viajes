
package Views;

import java.util.Scanner;

public abstract class MenuVistaBase implements MenuVista {
    protected Scanner entrada;
    
    public MenuVistaBase() {
        this.entrada = new Scanner(System.in);
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
    
    @Override
    public String leerDato(String campo) {
        System.out.print(campo + ": ");
        return entrada.nextLine().trim();
    }
}
