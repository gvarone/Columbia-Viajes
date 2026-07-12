package DAOs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class DAO<T> {

    protected static final String CARPETA_DATOS = "data/";
    private String ruta;

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
                    .collect(java.util.stream.Collectors.toList());

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

    protected void registrar(T item, Function<String, T> mapeador, Function<T, String> formateador) {
        List<T> actuales = leerTodos(mapeador);
        actuales.add(item);
        guardarTodos(actuales, formateador);
    }
    
    protected void modificar(T item, Function<String, T> mapeador, 
            Function<T, String> formateador, Function<T, Integer> obtenerCodigo) {
        List<T> actuales = leerTodos(mapeador);
        for (int i = 0; i < actuales.size(); i++) {
            if (obtenerCodigo.apply(actuales.get(i)).equals(obtenerCodigo.apply(item))) {
                actuales.set(i, item);
                break;
            }
        }
        guardarTodos(actuales, formateador);
    }
}
