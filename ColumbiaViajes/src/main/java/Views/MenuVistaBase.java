
package Views;

import Enums.Clase;
import Enums.Hospedaje;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public abstract class MenuVistaBase implements MenuVista {
    protected Scanner entrada;
    
    public MenuVistaBase() {
        this.entrada = new Scanner(System.in);
    }
    
    @Override
    public int leerOpcion(int max) {
        int opt = 0;
        boolean esValido = false;
        do{
            try {
                opt = Integer.parseInt(entrada.nextLine().trim());
                if(opt >= 0 && opt <= max){
                    esValido = true;
                }                
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
    public int leerEntero(String campo, boolean isPositivo) {
        System.out.print(campo + ": ");
        int num = 0;
        boolean esValido = false;
        
        do{
            try {
                num = Integer.parseInt(entrada.nextLine().trim());
                esValido = true;
                if(isPositivo && num < 0){
                    esValido = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debe ingresar un número entero.");
            }
        } while(!esValido);
        
        return num;
    }
    
    @Override
    public Integer leerEnteroOpcional(String campo, boolean isPositivo) {
        System.out.print(campo + ": ");
        boolean esValido = false;
        Integer num = null;
        do{
            System.out.print(campo + ": ");
            String texto = entrada.nextLine().trim();
            if (texto.isEmpty()) {
                return null; 
            }
            try {
                num = Integer.parseInt(texto);
                esValido = true;
                if(isPositivo && num < 0){
                    esValido = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debe ingresar un número entero, o dejar vacío para omitir.");
            }
        } while(!esValido);
        return num;
    }
    
    @Override
    public LocalDateTime leerDatoFecha(String campo) {
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
    public Hospedaje leerHospedaje(){
        System.out.print("Ingrese el tipo de hospedaje deseado: ");
        System.out.print("1- Media Pension.");
        System.out.print("2- Pension Completa.");
        int opcion = this.leerOpcion(2);
        return (opcion == 1) ? Hospedaje.MEDIA_PENSION : Hospedaje.PENSION_COMPLETA;
    }
    
    @Override
    public Clase leerClase(){
        System.out.print("Ingrese la clase deseada: ");
        System.out.print("1- Turista.");
        System.out.print("2- Primera.");
        int opcion = this.leerOpcion(2);
        return (opcion == 1) ? Clase.TURISTA : Clase.PRIMERA;
    }
}
