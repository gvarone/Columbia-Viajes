
package Controllers;

import Enums.Rol;
import Modelos.Hotel;
import Modelos.Sucursal;
import Modelos.Turista;
import Modelos.Usuario;
import Modelos.Vuelo;
import Views.MenuVista;
import Services.SucursalService;
import Services.HotelService;
import Services.VueloService;
import Services.UsuarioService;
import Services.TuristaService;
import Views.RegistroVista;
import java.time.LocalDateTime;
import java.util.List;

public class AdminControlador {
    private Usuario usuario;
    private MenuVista menuVista;
    private SucursalService sucursalService;
    private HotelService hotelService;
    private VueloService vueloService;
    private UsuarioService usuarioService;
    private TuristaService turistaService;
    
    public AdminControlador(Usuario usuario, MenuVista menuVista) {
        this.usuario = usuario;
        this.menuVista = menuVista;
        this.sucursalService = new SucursalService();
        this.hotelService = new HotelService();
        this.vueloService = new VueloService();
        this.usuarioService = new UsuarioService();
        this.turistaService = new TuristaService();
    }
    
    public void manejarMenu() {
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
                case 4:
                    agregarUsuario();
                    break;
                case 5:
                    listarSucursales();
                    break;
                case 6:
                    listarHoteles();
                    break;
                case 7:
                    listarVuelos();
                    break;
                case 8:
                    listarClientes();
                    break;
                case 9:
                    listarTuristas();
                    break;
                case 10:
                    modificarSucursal();
                    break;
                case 11:
                    modificarHotel();
                    break;
                case 14:
                    eliminarSucursal();
                    break;
                case 15:
                    eliminarHotel();
                    break;
                case 16:
                    eliminarVuelo();
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no implementada todavía.");
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
    
    private void modificarSucursal() {
        int codigo = menuVista.leerEntero("Ingrese el Codigo: ", true);
        Sucursal sucursal = sucursalService.obtenerPorCodigo(codigo);
        if (sucursal == null) {
            System.out.println("No se encontró ninguna sucursal con ese criterio.");
            return;
        }
        System.out.println("Sucursal encontrada: " + sucursal);
        String nuevoNombre = menuVista.leerString("Nuevo Nombre (Enter para no modificar)");
        String nuevaDireccion = menuVista.leerString("Nueva dirección (Enter para no modificar)");
        String nuevoEmail = menuVista.leerString("Nuevo email (Enter para no modificar)");
        String nuevoTelefono = menuVista.leerString("Nuevo teléfono (Enter para no modificar)");
        
        if (!nuevoNombre.isEmpty()) sucursal.setNombre(nuevoNombre);
        if (!nuevaDireccion.isEmpty()) sucursal.setDireccion(nuevaDireccion);
        if (!nuevoEmail.isEmpty()) sucursal.setEmail(nuevoEmail);
        if (!nuevoTelefono.isEmpty()) sucursal.setTelefono(nuevoTelefono);
        
        sucursalService.modificar(sucursal);
        System.out.println("Sucursal modificada con éxito.");
    }
    
    private void listarSucursales() {
        List<Sucursal> sucursales = sucursalService.listar();
        if (sucursales.isEmpty()) {
            System.out.println("No hay sucursales registradas.");
            return;
        }
        for (Sucursal s : sucursales) {
            System.out.println(s);
        }
    }
    
    private void eliminarSucursal() {
        int codigo = menuVista.leerEntero("Ingrese el Codigo: ", true);
        Sucursal sucursal = sucursalService.obtenerPorCodigo(codigo);
        if (sucursal == null) {
            System.out.println("No se encontró ninguna sucursal con ese criterio.");
            return;
        }
        System.out.println("Sucursal encontrada: " + sucursal);
        sucursalService.eliminar(sucursal);
        System.out.println("Sucursal eliminada con éxito.");
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
    
    private void modificarHotel() {
        int codigo = menuVista.leerEntero("Ingrese el Codigo: ", true);
        Hotel hotel = hotelService.obtenerPorCodigo(codigo);
        if (hotel == null) {
            System.out.println("No se encontró ningun hotel con ese criterio.");
            return;
        }
        System.out.println("Hotel encontrado: " + hotel);
        String nuevoNombre = menuVista.leerString("Nuevo Nombre (Enter para no modificar)");
        String nuevaDireccion = menuVista.leerString("Nueva dirección (Enter para no modificar)");
        String nuevaCiudad = menuVista.leerString("Nueva ciudad (Enter para no modificar)");
        String nuevoTelefono = menuVista.leerString("Nuevo teléfono (Enter para no modificar)");
        
        if (!nuevoNombre.isEmpty()) hotel.setNombre(nuevoNombre);
        if (!nuevaDireccion.isEmpty()) hotel.setDireccion(nuevaDireccion);
        if (!nuevaCiudad.isEmpty()) hotel.setCiudad(nuevaCiudad);
        if (!nuevoTelefono.isEmpty()) hotel.setTelefono(nuevoTelefono);
        
        hotelService.modificar(hotel);
        System.out.println("Hotel modificada con éxito.");
    }
    
    private void listarHoteles() {
        List<Hotel> hoteles = hotelService.listar();
        if (hoteles.isEmpty()) {
            System.out.println("No hay hoteles registrados.");
            return;
        }
        for (Hotel h : hoteles) {
            System.out.println(h);
        }
    }
    
    private void eliminarHotel() {
        int codigo = menuVista.leerEntero("Ingrese el Codigo: ", true);
        Hotel hotel = hotelService.obtenerPorCodigo(codigo);
        if (hotel == null) {
            System.out.println("No se encontró ningun hotel con ese criterio.");
            return;
        }
        System.out.println("Hotel encontrado: " + hotel);
        hotelService.eliminar(hotel);
        System.out.println("Hotel eliminado con éxito.");
    }
    
    private void agregarVuelo() {
        String origen = menuVista.leerString("Origen");
        String destino = menuVista.leerString("Destino");
        LocalDateTime fechaHora = menuVista.leerDatoFecha("Fecha y hora de salida");
        int asientosTotales = menuVista.leerEntero("Asientos Totales",true);
        int asientosTurista = menuVista.leerEntero("Plazas Totales",true);

        vueloService.registrar(origen, destino, fechaHora, asientosTotales, 
                asientosTurista);
        System.out.println("Vuelo registrado con éxito.");
    }
    
    private void listarVuelos() {
        List<Vuelo> vuelos = vueloService.listar();
        if (vuelos.isEmpty()) {
            System.out.println("No hay hoteles registrados.");
            return;
        }
        for (Vuelo v : vuelos) {
            System.out.println(v);
        }
    }
    
    private void eliminarVuelo() {
        int codigo = menuVista.leerEntero("Ingrese el Codigo: ", true);
        Vuelo vuelo = vueloService.obtenerPorCodigo(codigo);
        if (vuelo == null) {
            System.out.println("No se encontró ningun hotel con ese criterio.");
            return;
        }
        System.out.println("Vuelo encontrado: " + vuelo);
        vueloService.eliminar(vuelo);
        System.out.println("Vuelo eliminado con éxito.");
    }
    
    private void agregarUsuario() {
        RegistroVista registroVista = new RegistroVista();
        registroVista.mostrarInicio();
        int rol = menuVista.leerOpcion();
        String nombre = registroVista.leerNombre();
        String apellido = registroVista.leerApellido();
        String username = registroVista.leerUsername();
        String contrasenia = registroVista.leerContrasenia();
        switch (rol) {
            case 1:
                int codTurista = menuVista.leerEntero("Código de turista asociado",true);
                usuarioService.registrarCliente(nombre, apellido, username, contrasenia, codTurista);
                break;
            case 2:
                usuarioService.registrarVendedor(nombre, apellido, username, contrasenia);
                break;
            case 3:
                usuarioService.registrarAdministrador(nombre, apellido, username, contrasenia);
                break;
            case 4:
                usuarioService.registrarDuenio(nombre, apellido, username, contrasenia);
                break;
            default:
                System.out.println("Opción de tipo de usuario inválida.");
                return;
        }
        System.out.println("Usuario creado. Ya podés iniciar sesión.");
    }

    private void listarClientes() {
        List<Usuario> clientes = usuarioService.listarUsuariosXRol(Rol.CLIENTE);
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        System.out.println("Clientes: ");
        for (Usuario c : clientes) {
            System.out.println(c);
        }
    }

    private void listarTuristas() {
        List<Turista> turistas = turistaService.listar();
        if (turistas.isEmpty()) {
            System.out.println("No hay clientes ni turistas registrados.");
            return;
        }
        System.out.println("Turistas: ");
        for (Turista t : turistas) {
            System.out.println(t);
        }
    }
}
