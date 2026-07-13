
package Views;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MenuVistaBase implements MenuVista {
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
    public String leerString(String campo) {
        System.out.print(campo + ": ");
        return entrada.nextLine().trim();
    }
    
    @Override
    public int leerEntero(String campo, boolean positivo) {
        System.out.print(campo + ": ");
        int num = 0;
        boolean esValido = false;
        
        do{
            try {
                num = Integer.parseInt(entrada.nextLine().trim());
                esValido = true;
                if(positivo && num < 0){
                    esValido = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debe ingresar un número entero.");
            }
        } while(!esValido);
        
        return num;
    }
    
    @Override
    public LocalDateTime leerDatoFechaHora(String campo) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        while (true) {
            System.out.print(campo + " (dd/MM/yyyy HH:mm): ");
            try {
                return LocalDateTime.parse(entrada.nextLine().trim(), formato);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido.");
            }
        }
    }

    @Override
    public LocalDate leerDatoFecha(String campo){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while(true){
            System.out.print(campo + " (dd/MM/yyyy): ");
            try {
                return LocalDate.parse(entrada.nextLine().trim(), formato);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido.");
            }
        }
    }

    @Override
    public void mostrarMenu() {
        System.out.println("No hay menu actualmente");
    }
}
