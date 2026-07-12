
package Views;

import java.util.Scanner;

public class RegistroVista {
    private Scanner entrada;

    public RegistroVista() {
        this.entrada = new Scanner(System.in);
    }

    public void mostrarInicio() {
        System.out.println("--- Registro de usuario ---");
        System.out.println("¿Qué tipo de usuario quiere crear?");
        System.out.println("1- Cliente");
        System.out.println("2- Vendedor");
        System.out.println("3- Administrador");
        System.out.println("4- Dueño");
    }

    public String leerNombre() {
        System.out.print("Nombre: ");
        return entrada.nextLine().trim();
    }

    public String leerApellido() {
        System.out.print("Apellido: ");
        return entrada.nextLine().trim();
    }

    public String leerUsername() {
        System.out.print("Username: ");
        return entrada.nextLine().trim();
    }

    public String leerContrasenia() {
        System.out.print("Contraseña: ");
        return entrada.nextLine().trim();
    }
}
