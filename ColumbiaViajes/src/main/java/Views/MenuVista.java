
package Views;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface MenuVista {
    void mostrarMenu();
    int leerOpcion();
    String leerString(String campo);
    int leerEntero(String campo, boolean positivo);
    LocalDateTime leerDatoFechaHora(String campo);
    LocalDate leerDatoFecha(String campo);
}
