
package Services;
import java.util.List;

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

    public void listarDisponibilidad(){
        List<Hotel> hoteles = hotelDAO.listar();

        if (hoteles.size() == 0) {
            System.out.println("No hay hoteles disponibles actualmente");
            return;
        }

        for(Hotel h : hoteles){
            if(h.getPlazasDisponibles() > 0){
                System.out.println("Nombre: " + h.getNombre() + " | Ciudad: " + h.getCiudad() + " | Telefono: " + h.getTelefono() + " | Disponibilidad: " + h.getPlazasDisponibles());
            }
        }
    }

    public boolean tieneDisponibilidad(int codHotel){
        Hotel hotel = hotelDAO.obtenerPorCodigo(codHotel);

        if(hotel == null){
            return false;
        }

        return hotel.getPlazasDisponibles() > 0;
    }
}
