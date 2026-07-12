
package Services;
import DAOs.TuristaDAO;
import Modelos.Turista;
import java.util.List;

public class TuristaService {
    private TuristaDAO turistaDAO;
    
    public TuristaService() {
        this.turistaDAO = new TuristaDAO();
    }
    
    public Turista registrar(String nombre, String apellido, String direccion,
            String telefono, String email) {
        if (turistaDAO.obtenerPorNombre(nombre) != null) {
            throw new RuntimeException("Ya existe un/a turista con ese nombre");
        }
        int codigo = turistaDAO.obtenerUltimoCodigo();
        Turista turista = new Turista(codigo, nombre, apellido, direccion, 
                telefono, email);
        turistaDAO.registrar(turista);
        return turista;
    }
    
    public List<Turista> listar() {
        return turistaDAO.listar();
    }
    
    public Turista obtenerPorCodigo(int codigo) {
        return turistaDAO.obtenerPorCodigo(codigo);
    }
}
