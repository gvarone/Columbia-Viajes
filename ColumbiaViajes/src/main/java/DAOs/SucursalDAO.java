
package DAOs;

import Modelos.Sucursal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SucursalDAO extends DAO<Sucursal> {
    
    public SucursalDAO() {
        super(CARPETA_DATOS + "sucursales.dat");
    }
    
    Function<String, Sucursal> mapeador = linea ->{
        String[] datos = linea.split(";");
        
        int codigo = Integer.parseInt(datos[0].trim());
        String direccion = datos[1].trim();
        String email = datos[2].trim();
        String telefono = datos[3].trim();
        
        return new Sucursal(codigo, direccion, email, telefono);
    };
    
    Function<Sucursal, String> formateador = sucursal -> {
        return sucursal.getCodigo() + ";" + sucursal.getDireccion() + ";" + sucursal.getEmail() + ";" + sucursal.getTelefono() + ";";
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
    
    public void escribirLista(List<Sucursal> lista){
        guardarTodos(lista, formateador);
    }
    
    public void escribir(Sucursal sucursal){
        guardarUno(sucursal, formateador);
    }
    
    public void modificarUno(Sucursal sucursalNew){
        modificar(sucursalNew, mapeador, formateador, suc -> suc.getCodigo() == sucursalNew.getCodigo());
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
}
