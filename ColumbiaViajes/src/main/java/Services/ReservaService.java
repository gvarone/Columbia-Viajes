
package Services;

import DAOs.ReservaDAO;
import Modelos.Reserva;
import Enums.Clase;
import Enums.Hospedaje;
import java.time.LocalDateTime;
import java.util.List;

public class ReservaService {
    private ReservaDAO reservaDAO;

    public ReservaService() {
        this.reservaDAO = new ReservaDAO();
    }
    
    public Reserva crear(int codTurista, int codVendedor, int codSucursal,
            Integer codVuelo, Clase clase, Integer codHotel, Hospedaje hospedaje,
            LocalDateTime checkIn, LocalDateTime checkOut) {

        int codigo = reservaDAO.obtenerUltimoCodigo();
        Reserva reserva = new Reserva(codigo, codTurista, codVendedor, codSucursal);

        if (codVuelo != null) {
            reserva.agregarVuelo(codVuelo, clase);
        }
        if (codHotel != null) {
            reserva.agregarHotel(codHotel, hospedaje, checkIn, checkOut);
        }

        reservaDAO.registrar(reserva);
        return reserva;
    }
    
    public List<Reserva> listar() {
        return reservaDAO.listar();
    }

    public Reserva obtenerPorCodigo(int codigo) {
        return reservaDAO.obtenerPorCodigo(codigo);
    }
}
