package Services;

import DAOs.UsuarioDAO;
import Modelos.Usuario;

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
    
    public void registrarUsuario(String username, String contrasenia){
        Usuario usuario = usuarioDAO.obtenerPorUsername(username);
        if (usuario == null){
            
        }
    }
}
