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
public class VendedorMenuVista implements MenuVista {
    private Scanner entrada;
    
    @Override
    public void mostrarMenu() {
        System.out.println("---Menu Vendedores---");
        System.out.println("1- Listar clientes actuales");
        System.out.println("2- Buscar un cliente por nombre");
        System.out.println("3- Asignar un paquete a un cliente");
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
