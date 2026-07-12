package DAOs;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import Modelos.Turista;

public class TuristaDAO extends DAO<Turista> {

    public TuristaDAO() {
        super(CARPETA_DATOS + "Turistas.txt");
    }

    Function<String, Turista> mapeador = linea ->{
        String[] datos = linea.split(";");
        
        int codigo = Integer.parseInt(datos[0].trim());
        String nombre = datos[1].trim();
        String apellido = datos[2].trim();
        String direccion = datos[3].trim();
        String telefono = datos[4].trim();
        String email = datos[5].trim();
        
        Turista turista = new Turista(codigo, nombre, apellido, direccion, telefono, email);
        return turista;
    };
    
    Function<Turista, String> formateador = turista -> {
        return turista.getCodigo() + ";" + turista.getNombre() + ";" + 
                turista.getApellido() + ";" + turista.getDireccion() + ";" +
                turista.getTelefono() + ";" + turista.getEmail() + ";";
    };
    
    public int obtenerUltimoCodigo() {
        List<Turista> turistas = leerTodos(mapeador);
        int maximo = 0;
        for (Turista t : turistas) {
            if (t.getCodigo() > maximo) {
                maximo = t.getCodigo();
            }
        }
        
        return maximo + 1;
    }
    
    public List<Turista> listar() {
        List<Turista> todos = leerTodos(mapeador);
        List<Turista> resultado = new ArrayList<>();
        for (Turista t : todos) {
            resultado.add(t);
        }
        return resultado;
    }
    
    public void escribirLista(List<Turista> lista){
        guardarTodos(lista, formateador);
    }
    
    public void escribir(Turista turista){
        guardarUno(turista, formateador);
    }
    
    public void modificarUno(Turista turistaNew){
        modificar(turistaNew, mapeador, formateador, turista -> turista.getCodigo() == turistaNew.getCodigo());
    }
    
    public Turista obtenerPorCodigo(int codigo) {
        List<Turista> todos = leerTodos(mapeador);
        for (Turista t : todos) {
            if (t.getCodigo() == codigo) {
                return t;
            }
        }
        return null;
    }
    
    public Turista obtenerPorNombre(String nombre){
        List<Turista> todos = leerTodos(mapeador);
        for (Turista t: todos){
            if(t.getNombre().equals(nombre)){
                return t;
            }
        }
        return null;
    }
   
    public void registrar(Turista turista) {
        super.registrar(turista, mapeador, formateador);
    }

}
