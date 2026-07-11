
package Modelos;

import java.io.Serializable;
import java.time.LocalDateTime;


public class Vuelo implements Serializable {
    private final int codigo;
    private String origen;
    private String destino;
    private final int plazasTotales;
    private final int plazasTurista;
    private int plazasTuristaDisponibles;
    private int plazasPrimeraDisponibles;
    private LocalDateTime fecha_hora;
    private boolean eliminado = false;

    public Vuelo(int codigo, String origen, String destino, int plazasTotales, 
            int plazasTurista, LocalDateTime fecha_hora) {
        this.codigo = codigo;
        this.origen = origen;
        this.destino = destino;
        this.plazasTotales = plazasTotales;
        this.plazasTurista = plazasTurista;
        this.plazasTuristaDisponibles = plazasTurista;
        this.plazasPrimeraDisponibles = plazasTotales - plazasTurista;
        this.fecha_hora = fecha_hora;
    }
    
    public boolean isEliminado(){
        return eliminado;
    }
    
    public void eliminar(){
        this.eliminado = true;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }
    
    public LocalDateTime getFechaHora() {
        return fecha_hora;
    }

    public int getPlazasTotales() {
        return plazasTotales;
    }

    public int getPlazasTurista() {
        return plazasTurista;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
    
    public void setFechaHora(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public int getPlazasTuristaDisponibles() {
        return plazasTuristaDisponibles;
    }
    
    
}
