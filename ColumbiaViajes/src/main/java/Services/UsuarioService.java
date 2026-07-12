package Services;

import DAOs.UsuarioDAO;
import Enums.Rol;
import Modelos.Usuario;
import Modelos.Cliente;
import Modelos.Vendedor;
import Modelos.Administrador;
import Modelos.Duenio;
import java.util.List;

public class UsuarioService {

    private UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public Usuario iniciarSesion(String username, String contrasenia) {
        Usuario usuario = usuarioDAO.obtenerPorUsername(username);
        if (usuario == null) {
            return null; 
        }
        if (!usuario.validarContrasenia(contrasenia)) {
            return null; 
        }
        return usuario; 
    }
    
    public List<Usuario> listarUsuariosXRol(Rol rol){
        return usuarioDAO.listarXRol(rol);
    }
    
    public Cliente registrarCliente(String nombre, String apellido, String username,
            String contrasenia, int codTurista) {
        if (usuarioDAO.obtenerPorUsername(username) != null) {
            throw new RuntimeException("Ya existe un usuario con ese nombre de usuario");
        }
        int codigo = usuarioDAO.obtenerUltimoCodigo();
        Cliente cliente = new Cliente(codigo, nombre, apellido, contrasenia, codTurista, username);
        usuarioDAO.registrar(cliente);
        return cliente;
    }
    
    public Vendedor registrarVendedor(String nombre, String apellido, String username,
            String contrasenia) {
        if (usuarioDAO.obtenerPorUsername(username) != null) {
            throw new RuntimeException("Ya existe un usuario con ese nombre de usuario");
        }
        int codigo = usuarioDAO.obtenerUltimoCodigo();
        Vendedor vendedor = new Vendedor(codigo, nombre, apellido, contrasenia, username);
        usuarioDAO.registrar(vendedor);
        return vendedor;
    }
    
    public Administrador registrarAdministrador(String nombre, String apellido, String username,
            String contrasenia) {
        if (usuarioDAO.obtenerPorUsername(username) != null) {
            throw new RuntimeException("Ya existe un usuario con ese nombre de usuario");
        }
        int codigo = usuarioDAO.obtenerUltimoCodigo();
        Administrador administrador = new Administrador(codigo, nombre, apellido, contrasenia, username);
        usuarioDAO.registrar(administrador);
        return administrador;
    }
    
    public Duenio registrarDuenio(String nombre, String apellido, String username,
            String contrasenia) {
        if (usuarioDAO.obtenerPorUsername(username) != null) {
            throw new RuntimeException("Ya existe un usuario con ese nombre de usuario");
        }
        int codigo = usuarioDAO.obtenerUltimoCodigo();
        Duenio duenio = new Duenio(codigo, nombre, apellido, contrasenia, username);
        usuarioDAO.registrar(duenio);
        return duenio;
    }
    
    public boolean hayUsuariosRegistrados() {
        return !usuarioDAO.listar().isEmpty();
    }
}
