/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import java.util.Scanner;

/**
 *
 * @author Ramiro
 */
public class AdminMenuVista implements MenuVista {
    private Scanner entrada;
    
    @Override
    public void mostrarMenu() {
        System.out.println("---Menu Administradores---");
        System.out.println("1- Agregar una Sucursal");
        System.out.println("2- Agregar un Hotel");
        System.out.println("3- Agregar un vuelo");
        System.out.println("4- Listar sucursales");
        System.out.println("5- Listar hoteles");
        System.out.println("6- Listar vuelos");
        System.out.println("7- Listar clientes/turistas");
        System.out.println("8- Modificar sucursales");
        System.out.println("9- Modificar hoteles");
        System.out.println("10- Modificar vuelos");
        System.out.println("11- Modificar clientes/turistas");
        System.out.println("12- Eliminar sucursales");
        System.out.println("13- Eliminar hoteles");
        System.out.println("14- Eliminar vuelos");
        System.out.println("15- Eliminar clientes/turistas");
        System.out.println("16- Asignar un rol a un usuario");
    }

    @Override
    public int leerOpcion() {
        entrada = new Scanner(System.in);
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
