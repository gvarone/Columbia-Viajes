
package DAOs;

import Modelos.Sucursal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SucursalDAO extends DAO<Sucursal> {
    
    public SucursalDAO() {
        super(CARPETA_DATOS + "sucursales.txt");
    }
    
    Function<String, Sucursal> mapeador = linea ->{
        String[] datos = linea.split(";");
        
        int codigo = Integer.parseInt(datos[0].trim());
        String direccion = datos[1].trim();
        String email = datos[2].trim();
        String telefono = datos[3].trim();
        boolean eliminado = Boolean.parseBoolean(datos[4].trim());
        
        Sucursal sucursal = new Sucursal(codigo, direccion, email, telefono);
        if (eliminado) {
            sucursal.eliminar();
        }
        return sucursal;
    };
    
    Function<Sucursal, String> formateador = sucursal -> {
        return sucursal.getCodigo() + ";" + sucursal.getDireccion() + ";" + 
                sucursal.getEmail() + ";" + sucursal.getTelefono() + ";" +
                sucursal.isEliminado();
    };
    
    public int obtenerUltimoCodigo() {
        List<Sucursal> sucursales = leerTodos(mapeador);
        int maximo = 0;
        for (Sucursal s : sucursales) {
            if (s.getCodigo() > maximo) {
                maximo = s.getCodigo();
            }
        }
        return maximo + 1;
    }
    
    public List<Sucursal> listar() {
        List<Sucursal> todas = leerTodos(mapeador);
        List<Sucursal> resultado = new ArrayList<>();
        for (Sucursal s : todas) {
            if (!s.isEliminado()) {
                resultado.add(s);
            }
        }
        return resultado;
    }
    
    public Sucursal obtenerPorCodigo(int codigo) {
        List<Sucursal> todas = leerTodos(mapeador);
        for (Sucursal s : todas) {
            if (s.getCodigo() == codigo) {
                return s;
            }
        }
        return null;
    }
   
    public void registrar(Sucursal sucursal) {
        super.registrar(sucursal, mapeador, formateador);
    }
}
