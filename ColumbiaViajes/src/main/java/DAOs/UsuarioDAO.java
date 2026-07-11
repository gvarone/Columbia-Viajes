
package DAOs;

import Modelos.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends DAO<Usuario> {
    
    public UsuarioDAO() {
        super(CARPETA_DATOS + "usuarios.dat");
    }
    
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
