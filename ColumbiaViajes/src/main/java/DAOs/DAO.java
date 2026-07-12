package DAOs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class DAO<T> {

    protected static final String CARPETA_DATOS = "data/";
    private final String ruta;

    public DAO(String ruta) {
        this.ruta = ruta;
    }

    protected List<T> leerTodos(Function<String, T> mapeador) {
        Path path = Paths.get(ruta);
        if (!Files.exists(path)) {
            return new ArrayList<>();
        }
        try {
            return Files.lines(path)
                    .map(mapeador)
                    .collect(java.util.stream.Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Error al leer los datos desde " + ruta, e);
        }
    }

    protected void guardarTodos(List<T> lista, Function<T, String> formateador) {
        try {
            Path path = Paths.get(ruta);
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
            List<String> lineas = lista.stream()
                    .map(formateador)
                    .toList();
            
            Files.write(Paths.get(ruta), lineas);

            Files.write(path, lineas,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
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

    protected void registrar(T item, Function<String, T> mapeador, Function<T, String> formateador) {
        List<T> actuales = leerTodos(mapeador);
        actuales.add(item);
        guardarTodos(actuales, formateador);
    }
}
