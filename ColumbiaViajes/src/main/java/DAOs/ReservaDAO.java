
package DAOs;

import Enums.Clase;
import Enums.Hospedaje;
import Modelos.Reserva;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ReservaDAO extends DAO<Reserva> {
    public ReservaDAO() {
        super(CARPETA_DATOS + "reservas.txt");
    }
    
    Function<String, Reserva> mapeador = linea -> {
        String[] datos = linea.split(";", -1);

        int codigo = Integer.parseInt(datos[0].trim());
        int codTurista = Integer.parseInt(datos[1].trim());
        int codVendedor = Integer.parseInt(datos[2].trim());
        int codSucursal = Integer.parseInt(datos[3].trim());

        String codVueloTexto = datos[4].trim();
        Integer codVuelo = codVueloTexto.isEmpty() ? null : Integer.parseInt(codVueloTexto);

        String claseTexto = datos[5].trim();
        Clase clase = claseTexto.isEmpty() ? null : Clase.valueOf(claseTexto);

        String codHotelTexto = datos[6].trim();
        Integer codHotel = codHotelTexto.isEmpty() ? null : Integer.parseInt(codHotelTexto);

        String hospedajeTexto = datos[7].trim();
        Hospedaje hospedaje = hospedajeTexto.isEmpty() ? null : Hospedaje.valueOf(hospedajeTexto);

        String checkInTexto = datos[8].trim();
        LocalDateTime checkIn = checkInTexto.isEmpty() ? null : LocalDateTime.parse(checkInTexto, FORMATO_FECHA);

        String checkOutTexto = datos[9].trim();
        LocalDateTime checkOut = checkOutTexto.isEmpty() ? null : LocalDateTime.parse(checkOutTexto, FORMATO_FECHA);

        boolean eliminado = Boolean.parseBoolean(datos[10].trim());

        Reserva reserva = new Reserva(codigo, codTurista, codVendedor, codSucursal);
        if (codVuelo != null) {
            reserva.agregarVuelo(codVuelo, clase);
        }
        if (codHotel != null) {
            reserva.agregarHotel(codHotel, hospedaje, checkIn, checkOut);
        }
        if (eliminado) {
            reserva.eliminar();
        }
        return reserva;
    };
    
    Function<Reserva, String> formateador = reserva -> {
        String codVueloTexto = reserva.getCodVuelo() != null ? String.valueOf(reserva.getCodVuelo()) : "";
        String claseTexto = reserva.getClase() != null ? reserva.getClase().toString() : "";
        String codHotelTexto = reserva.getCodHotel() != null ? String.valueOf(reserva.getCodHotel()) : "";
        String hospedajeTexto = reserva.getHospedaje() != null ? reserva.getHospedaje().toString() : "";
        String checkInTexto = reserva.getCheckIn() != null ? reserva.getCheckIn().format(FORMATO_FECHA) : "";
        String checkOutTexto = reserva.getCheckOut() != null ? reserva.getCheckOut().format(FORMATO_FECHA) : "";

        return reserva.getCodigo() + ";" + reserva.getCodTurista() + ";" +
                reserva.getCodVendedor() + ";" + reserva.getCodSucursal() + ";" +
                codVueloTexto + ";" + claseTexto + ";" +
                codHotelTexto + ";" + hospedajeTexto + ";" +
                checkInTexto + ";" + checkOutTexto + ";" +
                reserva.isEliminado();
    };
    
    public int obtenerUltimoCodigo() {
        List<Reserva> reservas = leerTodos(mapeador);
        int maximo = 0;
        for (Reserva r : reservas) {
            if (r.getCodigo() > maximo) {
                maximo = r.getCodigo();
            }
        }
        return maximo + 1;
    }
    
    public List<Reserva> listar() {
        List<Reserva> todas = leerTodos(mapeador);
        List<Reserva> resultado = new ArrayList<>();
        for (Reserva r : todas) {
            if (!r.isEliminado()) {
                resultado.add(r);
            }
        }
        return resultado;
    }
    
    public Reserva obtenerPorCodigo(int codigo) {
        List<Reserva> todas = leerTodos(mapeador);
        for (Reserva r : todas) {
            if (r.getCodigo() == codigo) {
                return r;
            }
        }
        return null;
    }
    
    public void registrar(Reserva reserva) {
        super.registrar(reserva, mapeador, formateador);
    }

    public void modificar(Reserva reservaModificada) {
        super.modificar(reservaModificada, mapeador, formateador, Reserva::getCodigo);
    }
}
