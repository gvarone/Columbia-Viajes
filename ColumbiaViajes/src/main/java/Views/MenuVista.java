
package Views;

import Enums.Clase;
import Enums.Hospedaje;
import java.time.LocalDateTime;

public interface MenuVista {
    void mostrarMenu();
    int leerOpcion(int max);
    String leerString(String campo);
    int leerEntero(String campo, boolean isPositivo);
    Integer leerEnteroOpcional(String campo, boolean isPositivo);
    LocalDateTime leerDatoFecha(String campo);
    Hospedaje leerHospedaje();
    Clase leerClase();
}
