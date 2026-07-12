
package Controllers;

import Modelos.Usuario;
import Views.MenuVista;
import Views.LoginVista;
import Views.RegistroVista;
import Views.ClienteMenuVista;
import Views.VendedorMenuVista;
import Views.AdminMenuVista;
import Views.DuenioMenuVista;
import Services.UsuarioService;

public class UsuarioControlador {
    private Usuario usuario;
    private MenuVista menuVista;
    private LoginVista loginVista;
    private UsuarioService usuarioService;
    
    public UsuarioControlador() {
        this.usuarioService = new UsuarioService();
        this.loginVista = new LoginVista();
    }
    
    public void iniciar() {
        if (!usuarioService.hayUsuariosRegistrados()) {
            crearPrimerAdministrador();
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
        menuVista.mostrarMenu();
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
}
