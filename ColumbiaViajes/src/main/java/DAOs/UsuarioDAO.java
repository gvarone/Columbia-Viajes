
package DAOs;

import Enums.Rol;
import static Enums.Rol.CLIENTE;
import Modelos.Usuario;
import Modelos.Cliente;
import Modelos.Administrador;
import Modelos.Vendedor;
import Modelos.Duenio;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class UsuarioDAO extends DAO<Usuario> {
    
    public UsuarioDAO() {
        super(CARPETA_DATOS + "usuarios.txt");
    }
    
    Function<String, Usuario> mapeador = linea ->{
        String[] datos = linea.split(";");
        
        int codigo = Integer.parseInt(datos[0].trim());
        String nombre = datos[1].trim();
        String apellido = datos[2].trim();
        Rol rol = Rol.valueOf(datos[3].trim());
        String username = datos[4].trim();
        String contrasenia = datos[5].trim();
        boolean eliminado = Boolean.parseBoolean(datos[6].trim());
        
        Usuario usuario;
        switch (rol) {
            case CLIENTE:
                int codTurista = Integer.parseInt(datos[7].trim());
                usuario = new Cliente(codigo, nombre, apellido, contrasenia, codTurista, username);
                break;
            case VENDEDOR:
                usuario = new Vendedor(codigo, nombre, apellido, contrasenia, username);
                break;
            case ADMIN:
                usuario = new Administrador(codigo, nombre, apellido, contrasenia, username);
                break;
            case DUENIO:
                usuario = new Duenio(codigo, nombre, apellido, contrasenia, username);
                break;
            default:
                throw new RuntimeException("Rol desconocido: " + rol);
        }
        if (eliminado) {
            usuario.eliminar();
        }
        return usuario;
    };
    
    Function<Usuario, String> formateador = usuario -> {
        String linea = usuario.getCodigo() + ";" + usuario.getNombre() + ";" + 
                usuario.getApellido() + ";" + usuario.getRol() + ";" + 
                usuario.getUsername() + ";" + usuario.getContrasenia() + ";" +
                usuario.isEliminado();

        if (usuario instanceof Cliente) {
            linea += ";" + ((Cliente) usuario).getCodTurista();
        } else {
            linea += ";0";
        }
        
        return linea;
    };
    
    
    public int obtenerUltimoCodigo() {
        List<Usuario> usuarios = leerTodos(mapeador);
        int maximo = 0;
        for (Usuario s : usuarios) {
            if (s.getCodigo() > maximo) {
                maximo = s.getCodigo();
            }
        }
        return maximo + 1;
    }
    
    public List<Usuario> listar() {
        List<Usuario> todas = leerTodos(mapeador);
        List<Usuario> resultado = new ArrayList<>();
        for (Usuario u : todas) {
            if (!u.isEliminado()) {
                resultado.add(u);
            }
        }
        return resultado;
    }
    
    public List<Usuario> listarXRol(Rol rol) {
        List<Usuario> todas = leerTodos(mapeador);
        List<Usuario> resultado = new ArrayList<>();
        for (Usuario u : todas) {
            if (!u.isEliminado() && u.getRol() == rol) {
                resultado.add(u);
            }
        }
        return resultado;
    }
    
    public Usuario obtenerPorCodigo(int codigo) {
        List<Usuario> todas = leerTodos(mapeador);
        for (Usuario u : todas) {
            if (u.getCodigo() == codigo) {
                return u;
            }
        }
        return null;
    }
    
    public Usuario obtenerPorUsername(String username) {
        List<Usuario> todas = leerTodos(mapeador);
        for (Usuario u : todas) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }
   
    public void registrar(Usuario usuario) {
        super.registrar(usuario, mapeador, formateador);
    }
}
