
package Controllers;

import Modelos.Turista;
import Modelos.Usuario;
import Views.MenuVista;
import Views.MenuVistaBase;
import Views.LoginVista;
import Views.RegistroVista;
import Views.ClienteMenuVista;
import Views.VendedorMenuVista;
import Views.AdminMenuVista;
import Views.DuenioMenuVista;
import Services.UsuarioService;
import Services.SucursalService;
import Services.TuristaService;
import Services.HotelService;
import Services.PaqueteService;
import Services.VueloService;

import java.time.LocalDate;
import java.time.LocalDateTime;

import Enums.Clase;
import Enums.Hospedaje;
import Enums.Rol;


public class UsuarioControlador {
    private Usuario usuario;
    private MenuVista menuVista;
    private LoginVista loginVista;
    private UsuarioService usuarioService;
    private SucursalService sucursalService;
    private HotelService hotelService;
    private VueloService vueloService;
    private TuristaService turistaService;
    private PaqueteService paqueteService;
    
    public UsuarioControlador() {
        this.usuarioService = new UsuarioService();
        this.sucursalService = new SucursalService();
        this.hotelService = new HotelService();
        this.vueloService = new VueloService();
        this.turistaService = new TuristaService();
        this.paqueteService = new PaqueteService();
        this.loginVista = new LoginVista();
        this.menuVista = new MenuVistaBase();
        this.usuario = null;
    }
    
    public void iniciar() {
        if (!usuarioService.hayUsuariosRegistrados()) {
            crearPrimerAdministrador();
        } else{
            int optRegistro = menuVista.leerEntero("Ingrese 1 si desea registrarse\no cualquiero otro numero para iniciar sesion", true);

            if(optRegistro == 1){
                crearUsuario();
            }
        }
        
        usuario = null;
        int intentos = 0;
        final int MAX_INTENTOS = 3;
        
        while (usuario == null && intentos < MAX_INTENTOS){
            loginVista.mostrarInicio();
            String username = loginVista.leerUsername();
            if (username.equalsIgnoreCase("salir")){
                return;
            }
            String contrasenia = loginVista.leerContrasenia();

            usuario = usuarioService.iniciarSesion(username, contrasenia);

            if (usuario == null) {
                intentos++;
                System.out.println("Usuario o contraseña incorrectos. Intento " 
                        + intentos + " de " + MAX_INTENTOS + ".");
            }
        }
        if (usuario == null) {
            System.out.println("Se agotaron los intentos. Cerrando el sistema.");
            return;
        }
        System.out.println("Bienvenido, " + usuario.getNombre());
        asignarMenuSegunRol();
        switch (usuario.getRol()) {
            case ADMIN:
                manejarMenuAdmin();
                break;
            case CLIENTE:
                manejarMenuCliente();
                break;
            case VENDEDOR:
                manejarMenuVendedor();
                break;
            default:
                break;
        }
    }

    private void crearUsuario(){
        RegistroVista registroVista = new RegistroVista();

        registroVista.mostrarInicio();

        Rol rol = registroVista.leerRol();

        if(rol == null){
            System.out.println("Rol inexistente");
            return;
        }

        String nombre = registroVista.leerNombre();
        String apellido = registroVista.leerApellido();
        String username = registroVista.leerUsername();
        String contraseña = registroVista.leerContrasenia();

        switch (rol) {
            case CLIENTE:
                int codTurista = agregarTurista(nombre, apellido);
                usuarioService.registrarCliente(nombre, apellido, username, contraseña, codTurista);
                break;
            case VENDEDOR:
                usuarioService.registrarVendedor(nombre, apellido, username, contraseña);
                break;
            case ADMIN:
                usuarioService.registrarAdministrador(nombre, apellido, username, contraseña);
                break;
            case DUENIO:
                usuarioService.registrarDuenio(nombre, apellido, username, contraseña);
                break;
            default:
                break;
        }
        
    }
    
    private void crearPrimerAdministrador() {
        System.out.println("No hay usuarios en el sistema. Vamos a crear el primer Administrador.");
        RegistroVista registroVista = new RegistroVista();
        registroVista.mostrarInicio();
        String nombre = registroVista.leerNombre();
        String apellido = registroVista.leerApellido();
        String username = registroVista.leerUsername();
        String contrasenia = registroVista.leerContrasenia();

        usuarioService.registrarAdministrador(nombre, apellido, username, contrasenia);
        System.out.println("Administrador creado. Ya podés iniciar sesión.");
    }
    
    private void asignarMenuSegunRol() {
        switch (usuario.getRol()) {
            case CLIENTE:
                menuVista = new ClienteMenuVista();
                break;
            case VENDEDOR:
                menuVista = new VendedorMenuVista();
                break;
            case ADMIN:
                menuVista = new AdminMenuVista();
                break;
            case DUENIO:
                menuVista = new DuenioMenuVista();
                break;
        }
    }
    
    private void manejarMenuAdmin() {
        boolean salir = false;
        while (!salir) {
            menuVista.mostrarMenu();
            int opcion = menuVista.leerOpcion();
            switch (opcion) {
                case 1:
                    agregarSucursal();
                    break;
                case 2:
                    agregarHotel();
                    break;
                case 3:
                    agregarVuelo();
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no implementada todavía.");
            }
        }
    }

    private void manejarMenuVendedor(){
        boolean salir = false;
        while (!salir) {
            menuVista.mostrarMenu();
            int opcion = menuVista.leerOpcion();
            switch(opcion){
                case 3:
                    agregarPaquete();
                    break;
            }
        }
    }

    private void manejarMenuCliente(){
        boolean salir = false;
        while (!salir) {
            menuVista.mostrarMenu();
            int opcion = menuVista.leerOpcion();
            switch (opcion) {
                case 1:
                    hotelService.listarDisponibilidad();
                    break;
                case 2:
                    vueloService.listarDisponibilidad();
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inexistente");
                    break;
            }
        }
    }
    
    private void agregarSucursal() {
        String nombre = menuVista.leerString("Nombre de Sucursal");
        String direccion = menuVista.leerString("Dirección");
        String email = menuVista.leerString("Email");
        String telefono = menuVista.leerString("Teléfono");

        sucursalService.registrar(nombre, direccion, email, telefono);
        System.out.println("Sucursal registrada con éxito.");
    }
    
    private void agregarHotel() {
        String nombre = menuVista.leerString("Nombre del Hotel");
        String direccion = menuVista.leerString("Dirección");
        String email = menuVista.leerString("Email");
        String telefono = menuVista.leerString("Teléfono");
        int plazasTotales = menuVista.leerEntero("Plazas Totales",true);

        hotelService.registrar(nombre, direccion, email, telefono, plazasTotales);
        System.out.println("Hotel registrado con éxito.");
    }
    
    private void agregarVuelo() {
        String origen = menuVista.leerString("Origen");
        String destino = menuVista.leerString("Destino");
        LocalDateTime fechaHora = menuVista.leerDatoFechaHora("Fecha-Hora");
        int asientosTotales = menuVista.leerEntero("Asientos Totales",true);
        int asientosTurista = menuVista.leerEntero("Plazas Totales",true);

        vueloService.registrar(origen, destino, fechaHora, asientosTotales, 
                asientosTurista);
        System.out.println("Vuelo registrado con éxito.");
    }

    private int agregarTurista(String nombre, String apellido){
        String direccion = menuVista.leerString("Direccion");
        String telefono = menuVista.leerString("Telefono");
        String email = menuVista.leerString("Email");
        Turista turista = turistaService.registrar(nombre, apellido, direccion, telefono, email);
        System.out.println("Turista registrado con éxito.");

        return turista.getCodigo();
    }

    private void agregarPaquete(){
        boolean esValido = false;
        int opt = 0;

        int codTurista = menuVista.leerEntero("Codigo de turista", true);
        int codSucursal = menuVista.leerEntero("Codigo de sucursal", true);

        int codHotel = menuVista.leerEntero("Codigo de hotel", true);

        if(!hotelService.tieneDisponibilidad(codHotel)){
            System.out.println("Hotel inexistente o no disponible");
            return;
        }
        
        Hospedaje hospedaje = null;
        do {
            System.out.println("Tipo de hospedaje");
            System.out.println("1- Media pensión");
            System.out.println("2- pensión completa");

            opt = menuVista.leerOpcion();

            switch (opt) {
                case 1:
                    hospedaje = Hospedaje.MEDIA_PENSION;
                    esValido = true;
                    break;
                case 2:
                    hospedaje = Hospedaje.PENSION_COMPLETA;
                    esValido = true;
                    break;
                default:
                    System.out.println("Opcion inexistente");
                    break;
            }
        } while (!esValido);

        int codVuelo = menuVista.leerEntero("Codigo de vuelo", true);

        esValido = false;
        Clase clase = null;
        do{
            System.out.println("Clase vuelo");
            System.out.println("1- Turista");
            System.out.println("2- Primera");

            opt = menuVista.leerOpcion();

            switch (opt) {
                case 1:
                    clase = Clase.TURISTA;
                    esValido = true;
                    break;
                case 2:
                    clase = Clase.PRIMERA;
                    esValido = true;
                    break;
                default:
                    System.out.println("Opcion inexistente");
                    break;
            }
        }while(!esValido);

        if(!vueloService.tieneDisponibilidad(codVuelo, clase)){
            System.out.println("Vuelo inexistente o clase no disponible");
            return;
        }

        LocalDate checkIn = menuVista.leerDatoFecha("Check In");
        LocalDate checkOut = menuVista.leerDatoFecha("Check Out");

        paqueteService.registrar(codTurista, usuario.getCodigo(), codSucursal, codVuelo, codHotel, hospedaje, clase, checkIn, checkOut);
        System.out.println("Paquete asignado con exito");

    }
}
