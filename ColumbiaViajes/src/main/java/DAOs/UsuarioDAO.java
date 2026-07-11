
package DAOs;

import Enums.Rol;
import Modelos.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class UsuarioDAO extends DAO<Usuario> {
    
    public UsuarioDAO() {
        super(CARPETA_DATOS + "usuarios.dat");
    }
    
    Function<String, Usuario> mapeador = linea ->{
        String[] datos = linea.split(";");
        
        int codigo = Integer.parseInt(datos[0].trim());
        String nombre = datos[1].trim();
        String apellido = datos[2].trim();
        String contrasenia = datos[3].trim();
        Rol rol = Rol.parseRol(datos[4].trim());
        String username = datos[5].trim();
        boolean eliminado = Boolean.parseBoolean(datos[6].trim());
        
        return new Usuario(codigo, nombre, apellido, contrasenia, rol, username, eliminado);
    };
    
    Function<Usuario, String> formateador = usuario -> {
        return sucursal.getCodigo() + ";" + sucursal.getDireccion() + ";" + sucursal.getEmail() + ";" + sucursal.getTelefono() + ";";
    };
    
    public int obtenerUltimoCodigo() {
        List<Usuario> usuarios = leerTodos();
        int maximo = 0;
        for (Usuario u : usuarios) {
            if (u.getCodigo() > maximo) {
                maximo = u.getCodigo();
            }
        }
        return maximo + 1;
    }
    
    public List<Usuario> listar() {
        List<Usuario> todas = leerTodos();
        List<Usuario> resultado = new ArrayList<>();
        for (Usuario u : todas) {
            if (!u.isEliminado()) {
                resultado.add(u);
            }
        }
        return resultado;
    }
    
    public Usuario obtenerPorCodigo(int codigo) {
        List<Usuario> todas = leerTodos();
        for (Usuario u : todas) {
            if (u.getCodigo() == codigo) {
                return u;
            }
        }
        return null;
    }
    
    public Usuario obtenerPorNombre(String username) {
        List<Usuario> usuarios = leerTodos();
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }
    
    public boolean existeUsuario(String username) {
        return obtenerPorNombre(username) != null;
    }
}
