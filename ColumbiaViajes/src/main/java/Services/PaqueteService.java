package Services;

import java.time.LocalDate;

import DAOs.PaqueteDAO;
import Enums.Hospedaje;
import Enums.Clase;
import Modelos.Paquete;

public class PaqueteService {
    private PaqueteDAO paqueteDAO;
    
    public PaqueteService(){
        this.paqueteDAO = new PaqueteDAO();
    }

    public Paquete registrar(int codTurista, int codVendedor, int codSucursal, int codVuelo, int codHotel, Hospedaje hospedaje, Clase clase, LocalDate checkIn, LocalDate checkOut){
        int codPaquete = paqueteDAO.obtenerUltimoCodigo();
        Paquete paquete = new Paquete(codPaquete, codTurista, codVendedor, codSucursal, codVuelo, codHotel, hospedaje, clase, checkIn, checkOut);
        paqueteDAO.registrar(paquete);
        return paquete;
    }
}
