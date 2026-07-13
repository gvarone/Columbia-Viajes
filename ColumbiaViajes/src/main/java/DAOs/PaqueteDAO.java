package DAOs;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import Enums.Clase;
import Enums.Hospedaje;
import Modelos.Paquete;

public class PaqueteDAO extends DAO<Paquete> {
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public PaqueteDAO() {
        super(CARPETA_DATOS + "paquetes.txt");
    }

    Function<String, Paquete> mapeador = linea ->{
        String[] datos = linea.split(";");
        
        int codigo = Integer.parseInt(datos[0].trim());
        int codTurista = Integer.parseInt(datos[1].trim());
        int codVendedor = Integer.parseInt(datos[2].trim());
        int codSucursal = Integer.parseInt(datos[3].trim());
        int codVuelo = Integer.parseInt(datos[4].trim());
        int codHotel = Integer.parseInt(datos[5].trim());
        Hospedaje hospedaje = Hospedaje.valueOf(datos[6].trim());
        Clase clase = Clase.valueOf(datos[7].trim());
        LocalDate checkIn = LocalDate.parse(datos[8].trim());
        LocalDate checkOut = LocalDate.parse(datos[9].trim());

        Paquete paquete = new Paquete(codigo, codTurista, codVendedor, codSucursal, codVuelo, codHotel, hospedaje, clase, checkIn, checkOut);

        return paquete;
    };

    Function<Paquete, String> formateador = paquete -> {
        return paquete.getCodigo() + ";" +  
                paquete.getCodTurista() + ";" + 
                paquete.getCodVendedor() + ";" +
                paquete.getCodSucursal() + ";" + 
                paquete.getCodVuelo() + ";" + 
                paquete.getCodHotel() + ";" +
                paquete.getHospedaje() + ";" + 
                paquete.getClase() + ";" + 
                paquete.getCheckIn().format(FORMATO_FECHA) + ";" + 
                paquete.getCheckOut().format(FORMATO_FECHA) + ";";
    };    

    public int obtenerUltimoCodigo() {
        List<Paquete> paquetes = leerTodos(mapeador);
        int maximo = 0;
        for (Paquete p : paquetes) {
            if (p.getCodigo() > maximo) {
                maximo = p.getCodigo();
            }
        }
        return maximo + 1;
    }
    
    public List<Paquete> listar() {
        List<Paquete> todos = leerTodos(mapeador);
        List<Paquete> resultado = new ArrayList<>();
        for (Paquete p : todos) {
            resultado.add(p);
        }
        return resultado;
    }
    
    public void escribirLista(List<Paquete> lista){
        guardarTodos(lista, formateador);
    }
    
    public void escribir(Paquete paquete){
        guardarUno(paquete, formateador);
    }
    
    public void modificarUno(Paquete paqueteNew){
        modificar(paqueteNew, mapeador, formateador, paquete -> paquete.getCodigo() == paqueteNew.getCodigo());
    }    
    
    public Paquete obtenerPorCodigo(int codigo) {
        List<Paquete> todos = leerTodos(mapeador);
        for (Paquete p : todos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }
    
    public void registrar(Paquete paquete) {
        super.registrar(paquete, mapeador, formateador);
    }

}
