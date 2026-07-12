
package Views;

import java.util.Scanner;

public class LoginVista {  
    protected Scanner entrada;
    
    public LoginVista(){
        this.entrada = new Scanner(System.in);
    }
    
    public void mostrarInicio() {
        System.out.println("--- Iniciar sesión ---");
    }

    public String leerUsername() {
        System.out.print("Username (escriba salir para... bueno, salir: ");
        return entrada.nextLine().trim();
    }

    public String leerContrasenia() {
        System.out.print("Contraseña: ");
        return entrada.nextLine().trim();
    }
    
}
