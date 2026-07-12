
package Controllers;

import Enums.Rol;
import Modelos.Turista;
import Modelos.Usuario;
import Services.HotelService;
import Services.TuristaService;
import Services.VueloService;
import Views.MenuVista;
import java.util.List;

public class VendedorControlador {
    private Usuario usuario;
    private MenuVista menuVista;
    private HotelService hotelService;
    private VueloService vueloService;
    private TuristaService turistaService;
    
    public VendedorControlador(Usuario usuario, MenuVista menuVista) {
        this.usuario = usuario;
        this.menuVista = menuVista;
        this.hotelService = new HotelService();
        this.vueloService = new VueloService();
        this.turistaService = new TuristaService();
    }
    
    public void manejarMenu() {
        boolean salir = false;
        while (!salir) {
            menuVista.mostrarMenu();
            int opcion = menuVista.leerOpcion();
            switch (opcion) {
                case 1:
                    listarTuristas();
                    break;
                case 2:
                    buscarTuristaXNombre();
                    break;
                case 3:
                    cargarReserva();
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no implementada todavía.");
            }
        }
    }

    private void listarTuristas() {
        List<Turista> turistas = turistaService.listar();
        if (turistas.isEmpty()) {
            System.out.println("No hay turistas registrados.");
            return;
        }
        System.out.println("Turistas: ");
        for (Turista t : turistas) {
            System.out.println(t);
        }
    }

    private void buscarTuristaXNombre() {
        int codigo = menuVista.leerEntero("Ingrese el Codigo: ", true);
        Turista turista = turistaService.obtenerPorCodigo(codigo);
        if (turista == null) {
            System.out.println("No se encontró ningun/a turista con ese criterio.");
            return;
        }
        System.out.println("Turista encontrado: " + turista);
    }

    private void cargarReserva() {
        
    }
}
