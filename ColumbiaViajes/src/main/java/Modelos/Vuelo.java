
package Modelos;

import java.io.Serializable;
import java.time.LocalDateTime;


public class Vuelo implements Serializable {
    private int codigo;
    private String origen;
    private String destino;
    private final int plazas_totales;
    private final int plazas_turista;
    private int plazas_turista_disponibles;
    private int plazas_primera_disponibles;
    private LocalDateTime fecha_hora;

    public Vuelo(int codigo, String origen, String destino, int plazas_totales, 
            int plazas_turista, LocalDateTime fecha_hora) {
        this.codigo = codigo;
        this.origen = origen;
        this.destino = destino;
        this.plazas_totales = plazas_totales;
        this.plazas_turista = plazas_turista;
        this.plazas_turista_disponibles = plazas_turista;
        this.plazas_primera_disponibles = plazas_totales - plazas_turista;
        this.fecha_hora = fecha_hora;
        
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
        return plazas_totales;
    }

    public int getPlazasTurista() {
        return plazas_turista;
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
        return plazas_turista_disponibles;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    
}
