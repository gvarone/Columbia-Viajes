
package Views;

import java.util.Scanner;

import Enums.Rol;

public class RegistroVista {
    private Scanner entrada;

    public RegistroVista() {
        this.entrada = new Scanner(System.in);
    }

    public void mostrarInicio() {
        System.out.println("--- Registro de usuario ---");
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

    public Rol leerRol(){
        Rol rol;

        System.out.println("Rol: ");
        System.out.println("1- Cliente");
        System.out.println("2- Vendedor");
        System.out.println("3- Administrador");
        System.out.println("4- Dueño");
        int sRol = entrada.nextInt();
        entrada.nextLine();

        switch (sRol) {
            case 1:
                rol = Rol.CLIENTE;
                break;
            case 2:
                rol = Rol.VENDEDOR;
                break;
            case 3:
                rol = Rol.ADMIN;
                break;
            case 4:
                rol = Rol.VENDEDOR;
                break;
            default:
                rol = null;
                break;
        }
        
        return rol;
    }
}
