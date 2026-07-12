
package DAOs;

import Modelos.Vuelo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class VueloDAO extends DAO<Vuelo> {
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public VueloDAO() {
        super(CARPETA_DATOS + "vuelos.txt");
    }
    Function<String, Vuelo> mapeador = linea ->{
        String[] datos = linea.split(";");
        
        int codigo = Integer.parseInt(datos[0].trim());
        String origen = datos[1].trim();
        String destino = datos[2].trim();
        LocalDateTime fechaHora = LocalDateTime.parse(datos[3].trim(), FORMATO_FECHA);
        int asientosTotales =  Integer.parseInt(datos[4].trim());
        int asientosTurista =  Integer.parseInt(datos[5].trim());
        int asientosDisponibles = Integer.parseInt(datos[6].trim());
        int asientosTuristaDisponibles = Integer.parseInt(datos[7].trim());
        
        boolean eliminado = Boolean.parseBoolean(datos[9].trim());
        
        Vuelo vuelo = new Vuelo(codigo, origen, destino, fechaHora,
                asientosTotales, asientosTurista);
        vuelo.setAsientosDisponibles(asientosDisponibles);
        vuelo.setAsientosTuristaDisponibles(asientosTuristaDisponibles);
        if (eliminado) {
            vuelo.eliminar();
        }
        return vuelo;
    };
    
    Function<Vuelo, String> formateador = vuelo -> {
        return vuelo.getCodigo() + ";" + vuelo.getOrigen() + ";" + vuelo.getDestino() + ";"
                + vuelo.getFechaHora().format(FORMATO_FECHA) + ";"
                + vuelo.getAsientosTotales() + ";" + vuelo.getAsientosTurista() + ";"
                + vuelo.getAsientosDisponibles() + ";" + vuelo.getAsientosTuristaDisponibles() + ";"
                + vuelo.isEliminado() + ";";
    };
    
    public int obtenerUltimoCodigo() {
        List<Vuelo> vuelos = leerTodos(mapeador);
        int maximo = 0;
        for (Vuelo v : vuelos) {
            if (v.getCodigo() > maximo) {
                maximo = v.getCodigo();
            }
        }
        return maximo + 1;
    }
    
    public List<Vuelo> listar() {
        List<Vuelo> todas = leerTodos(mapeador);
        List<Vuelo> resultado = new ArrayList<>();
        for (Vuelo h : todas) {
            if (!h.isEliminado()) {
                resultado.add(h);
            }
        }
        return resultado;
    }
    
    public void escribirLista(List<Vuelo> lista){
        guardarTodos(lista, formateador);
    }
    
    public void escribir(Vuelo vuelo){
        guardarUno(vuelo, formateador);
    }
    
    public void modificarUno(Vuelo vueloNew){
        modificar(vueloNew, mapeador, formateador, vuelo -> vuelo.getCodigo() == vueloNew.getCodigo());
    }    
    
    public Vuelo obtenerPorCodigo(int codigo) {
        List<Vuelo> todas = leerTodos(mapeador);
        for (Vuelo h : todas) {
            if (h.getCodigo() == codigo) {
                return h;
            }
        }
        return null;
    }
    
    public void registrar(Vuelo vuelo) {
        super.registrar(vuelo, mapeador, formateador);
    }
}
