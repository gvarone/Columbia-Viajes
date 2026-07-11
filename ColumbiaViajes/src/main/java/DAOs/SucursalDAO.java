
package DAOs;

import Modelos.Sucursal;
import java.util.ArrayList;
import java.util.List;

public class SucursalDAO extends DAO<Sucursal> {
    
    public SucursalDAO() {
        super(CARPETA_DATOS + "sucursales.dat");
    }
    
    public int obtenerUltimoCodigo() {
        List<Sucursal> sucursales = leerTodos();
        int maximo = 0;
        for (Sucursal s : sucursales) {
            if (s.getCodigo() > maximo) {
                maximo = s.getCodigo();
            }
        }
        return maximo + 1;
    }
    
    public List<Sucursal> listar() {
        List<Sucursal> todas = leerTodos();
        List<Sucursal> resultado = new ArrayList<>();
        for (Sucursal s : todas) {
            if (!s.isEliminado()) {
                resultado.add(s);
            }
        }
        return resultado;
    }
    
    public Sucursal obtenerPorCodigo(int codigo) {
        List<Sucursal> todas = leerTodos();
        for (Sucursal s : todas) {
            if (s.getCodigo() == codigo) {
                return s;
            }
        }
        return null;
    }
}
