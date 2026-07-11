package DAOs;

import Modelos.Hotel;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO extends DAO<Hotel> {
    public HotelDAO() {
        super(CARPETA_DATOS + "hoteles.dat");
    }
    
    public int obtenerUltimoCodigo() {
        List<Hotel> hoteles = leerTodos();
        int maximo = 0;
        for (Hotel h : hoteles) {
            if (h.getCodigo() > maximo) {
                maximo = h.getCodigo();
            }
        }
        return maximo + 1;
    }
    
    public List<Hotel> listar() {
        List<Hotel> todas = leerTodos();
        List<Hotel> resultado = new ArrayList<>();
        for (Hotel h : todas) {
            if (!h.isEliminado()) {
                resultado.add(h);
            }
        }
        return resultado;
    }
    
    public Hotel obtenerPorCodigo(int codigo) {
        List<Hotel> todas = leerTodos();
        for (Hotel h : todas) {
            if (h.getCodigo() == codigo) {
                return h;
            }
        }
        return null;
    }
}
