
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

    public void listarDisponibilidad(){
        List<Vuelo> vuelos = vueloDAO.listar();

        if (vuelos.size() == 0) {
            System.out.println("No hay vuelos disponibles actualmente");
            return;
        }

        for(Vuelo v : vuelos){
            if(v.getAsientosTuristaDisponibles() > 0 || v.getAsientosPrimeraDisponibles() > 0){
                System.out.println("Origen: " + v.getOrigen() + " | Destino: " + v.getDestino() + " | fecha y hora: " + v.getFechaHora() + " | Asientos Turista Disponibles: " + v.getAsientosTuristaDisponibles() + " | Asientos Primera Disponibles: " + v.getAsientosPrimeraDisponibles());
            }
        }
    }    
}