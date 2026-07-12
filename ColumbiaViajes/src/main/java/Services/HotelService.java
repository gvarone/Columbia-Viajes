
package Services;
import DAOs.HotelDAO;
import Modelos.Hotel;

public class HotelService {
    private HotelDAO hotelDAO;
    
    public HotelService() {
        this.hotelDAO = new HotelDAO();
    }
    
     public Hotel registrar(String nombre, String direccion, String email,
            String telefono, int plazasTotales) {
        if (hotelDAO.obtenerPorNombre(nombre) != null) {
            throw new RuntimeException("Ya existe una hotel con ese nombre");
        }
        int codigo = hotelDAO.obtenerUltimoCodigo();
        Hotel hotel = new Hotel(codigo, nombre, direccion, email, telefono, plazasTotales);
        hotelDAO.registrar(hotel);
        return hotel;
    }
}
