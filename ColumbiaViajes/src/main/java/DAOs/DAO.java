package DAOs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class DAO<T> {

    protected static final String CARPETA_DATOS = "data/";
    private String ruta;

    public DAO(String ruta) {
        this.ruta = ruta;
    }

    protected List<T> leerTodos(Function<String, T> mapeador) {
        List<T> listaResultado = new ArrayList<>();

        try {
            listaResultado = Files.lines(Paths.get(ruta))
                    .map(mapeador)
                    .toList();

        } catch (IOException e) {
            throw new RuntimeException("Error al leer los datos desde " + ruta, e);
        }
        
        return listaResultado;
    }

    protected void guardarTodos(List<T> lista, Function<T, String> formateador) {
        try {
            List<String> lineas = lista.stream()
                    .map(formateador)
                    .toList();
            
            Files.write(Paths.get(ruta), lineas);

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar los datos en " + ruta, e);
        }
    }
    
    protected void guardarUno(T entidad, Function<T, String> formateador){
        try{
            String linea = formateador.apply(entidad);
            
            Files.write(Paths.get(ruta), List.of(linea), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e){
            throw new RuntimeException("Error al guardar los datos en " + ruta, e);
        }
    }
    
    protected void modificar(T entidadNew, Function<String, T> mapeador, Function<T, String> formateador, Predicate<T> condicionBusqueda){
        List<T> lista = leerTodos(mapeador);
        boolean encontrado = false;
        int i = 0;
        
        while(i < lista.size() && !encontrado){
            T entidadActual = lista.get(i);
            
            if (condicionBusqueda.test(entidadActual)){
                lista.set(i, entidadNew);
                encontrado = true;
            }
            
            i++;
        }
        
        if(encontrado){
            guardarTodos(lista, formateador);
        }
    }

    /*
    protected void registrar(T item) {
        List<T> actuales = leerTodos();
        actuales.add(item);
        guardarTodos(actuales);
    }
    */
}
