
package Services;
import DAOs.SucursalDAO;
import Modelos.Sucursal;
import java.util.List;

public class SucursalService {
    private SucursalDAO sucursalDAO;
    
    public SucursalService() {
        this.sucursalDAO = new SucursalDAO();
    }
    
     public Sucursal registrar(String nombre, String direccion, String email,
            String telefono) {
        if (sucursalDAO.obtenerPorNombre(nombre) != null) {
            throw new RuntimeException("Ya existe una sucursal con ese nombre");
        }
        int codigo = sucursalDAO.obtenerUltimoCodigo();
        Sucursal sucursal = new Sucursal(codigo, nombre, direccion, email, telefono);
        sucursalDAO.registrar(sucursal);
        return sucursal;
    }
    
    public List<Sucursal> listar() {
        return sucursalDAO.listar();
    }
    
    public Sucursal obtenerPorCodigo(int codigo) {
        return sucursalDAO.obtenerPorCodigo(codigo);
    }
    
    public void modificar(Sucursal sucursal) {
        sucursalDAO.modificar(sucursal);
    }
}
