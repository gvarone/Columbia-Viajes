
package Services;
import DAOs.VueloDAO;
import Modelos.Vuelo;
import java.time.LocalDateTime;

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
}