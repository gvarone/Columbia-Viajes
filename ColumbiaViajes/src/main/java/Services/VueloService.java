
package Services;
import DAOs.VueloDAO;
import Modelos.Vuelo;
import java.time.LocalDateTime;
import java.util.List;

public class VueloService {
    private VueloDAO vueloDAO;
    
    public VueloService() {
        this.vueloDAO = new VueloDAO();
    }
    
     public Vuelo registrar(String origen, String destino, LocalDateTime fechaHora,
            int asientosTotales, int asientosTurista) {
        int codigo = vueloDAO.obtenerUltimoCodigo();
        Vuelo vuelo = new Vuelo(codigo, origen, destino, fechaHora, asientosTotales, 
                asientosTurista );
        vueloDAO.registrar(vuelo);
        return vuelo;
    }

    public List<Vuelo> listar() {
        return vueloDAO.listar();
    }
    
    public Vuelo obtenerPorCodigo(int codigo) {
        return vueloDAO.obtenerPorCodigo(codigo);
    }
    
    public void modificar(Vuelo vuelo) {
        vueloDAO.modificar(vuelo);
    }
    
    public void eliminar(Vuelo vuelo) {
        vuelo.eliminar();
        vueloDAO.modificar(vuelo);
    }
}