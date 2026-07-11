package DAOs;

import java.io.Serializable;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DAO<T extends Serializable> {

    protected static final String CARPETA_DATOS = "data/";
    private String ruta;

    public DAO(String ruta) {
        this.ruta = ruta;
    }

    protected List<T> leerTodos() {
        File archivo = new File(ruta);

        if (!archivo.exists()) {
            return new ArrayList<>();
        }

        try (FileInputStream fis = new FileInputStream(archivo); ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (List<T>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error al leer los datos desde " + ruta, e);
        }
    }

    protected void guardarTodos(List<T> lista) {
        try (FileOutputStream fos = new FileOutputStream(ruta); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(lista);

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar los datos en " + ruta, e);
        }
    }

    protected void registrar(T item) {
        List<T> actuales = leerTodos();
        actuales.add(item);
        guardarTodos(actuales);
    }
}
