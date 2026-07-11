package DAOs;

import Modelos.Hotel;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class HotelDAO extends DAO<Hotel> {
    public HotelDAO() {
        super(CARPETA_DATOS + "hoteles.dat");
    }
    
    Function<String, Hotel> mapeador = linea ->{
        String[] datos = linea.split(";");
        
        int codigo = Integer.parseInt(datos[0].trim());
        String nombre = datos[1].trim();
        String direccion = datos[2].trim();
        String ciudad = datos[3].trim();
        String telefono = datos[4].trim();
        boolean eliminado = Boolean.parseBoolean(datos[5].trim());
        int pDisp = Integer.parseInt(datos[6].trim());
        int pTot = Integer.parseInt(datos[7].trim());
        
        return new Hotel(codigo, nombre, direccion, ciudad, telefono, eliminado, pDisp, pTot);
    };
    
    Function<Hotel, String> formateador = hotel -> {
        return hotel.getCodigo() + ";" + hotel.getNombre() + ";" + hotel.getDireccion() + ";" + hotel.getCiudad() + ";" + hotel.getTelefono() + ";" + hotel.isEliminado() + ";" + hotel.getPlazasDisponibles() + ";" + hotel.getPlazasTotales() + ";";
    };    
    
    public int obtenerUltimoCodigo() {
        List<Hotel> hoteles = leerTodos(mapeador);
        int maximo = 0;
        for (Hotel h : hoteles) {
            if (h.getCodigo() > maximo) {
                maximo = h.getCodigo();
            }
        }
        return maximo + 1;
    }
    
    public List<Hotel> listar() {
        List<Hotel> todas = leerTodos(mapeador);
        List<Hotel> resultado = new ArrayList<>();
        for (Hotel h : todas) {
            if (!h.isEliminado()) {
                resultado.add(h);
            }
        }
        return resultado;
    }
    
    public Hotel obtenerPorCodigo(int codigo) {
        List<Hotel> todas = leerTodos(mapeador);
        for (Hotel h : todas) {
            if (h.getCodigo() == codigo) {
                return h;
            }
        }
        return null;
    }
}
