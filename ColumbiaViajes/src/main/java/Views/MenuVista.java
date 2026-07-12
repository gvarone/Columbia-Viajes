
package Views;

import java.time.LocalDateTime;

public interface MenuVista {
    void mostrarMenu();
    int leerOpcion();
    String leerString(String campo);
    int leerEntero(String campo, boolean positivo);
    LocalDateTime leerDatoFecha(String campo);
}
