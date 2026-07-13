
package Controllers;

import Enums.Clase;
import Enums.Hospedaje;
import Enums.Rol;
import Modelos.Reserva;
import Modelos.Turista;
import Modelos.Usuario;
import Services.HotelService;
import Services.TuristaService;
import Services.VueloService;
import Services.ReservaService;
import Views.MenuVista;
import java.time.LocalDateTime;
import java.util.List;

public class VendedorControlador {
    private Usuario usuario;
    private MenuVista menuVista;
    private HotelService hotelService;
    private VueloService vueloService;
    private TuristaService turistaService;
    private ReservaService reservaService;
    
    public VendedorControlador(Usuario usuario, MenuVista menuVista) {
        this.usuario = usuario;
        this.menuVista = menuVista;
        this.hotelService = new HotelService();
        this.vueloService = new VueloService();
        this.turistaService = new TuristaService();
        this.reservaService = new ReservaService();
    }
    
    public void manejarMenu() {
        boolean salir = false;
        while (!salir) {
            menuVista.mostrarMenu();
            int opcion = menuVista.leerOpcion(3);
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
        int codigo = menuVista.leerEntero("Codigo: ", true);
        Turista turista = turistaService.obtenerPorCodigo(codigo);
        if (turista == null) {
            System.out.println("No se encontró ningun/a turista con ese criterio.");
            return;
        }
        System.out.println("Turista encontrado: " + turista);
    }

    private void cargarReserva() {
        boolean esValido = false;
        Integer codVuelo = null;
        Clase clase = null;
        Integer codHotel = null;
        Hospedaje hospedaje = null;
        LocalDateTime checkIn = null;
        LocalDateTime checkOut = null;
        int codVendedor = usuario.getCodigo();
        int codTurista = menuVista.leerEntero("Codigo de Turista", true);
        int codSucursal = menuVista.leerEntero("Codigo de Sucursal", true);
        do{
            codVuelo = menuVista.leerEnteroOpcional("Codigo de Vuelo (Enter para omitir)", true);
            if(codVuelo != null){
                clase = menuVista.leerClase();   
            }
            codHotel = menuVista.leerEnteroOpcional("Codigo de Hotel (Enter para omitir)", true);
            if(codHotel != null){
                hospedaje = menuVista.leerHospedaje();
                checkIn = menuVista.leerDatoFecha("Check-In");
                checkOut = menuVista.leerDatoFecha("Check-Out");
            }
            if(codVuelo != null || codHotel != null){
                esValido = true;
            }else{
                System.out.print("El paquete debe tener hotel y/o vuelo.");
            }
        } while(!esValido);
        
        try {
            reservaService.crear(codTurista, codVendedor, codSucursal,
                    codVuelo, clase, codHotel, hospedaje, checkIn, checkOut);
            System.out.println("Reserva cargada con éxito.");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
    }
}
