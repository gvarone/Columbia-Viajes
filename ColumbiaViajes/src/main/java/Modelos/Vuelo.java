package Modelos;

import java.time.LocalDateTime;

public class Vuelo {

    private final int codigo;
    private String origen;
    private String destino;
    private final int asientosTotales;
    private final int asientosTurista;
    private int asientosTuristaDisponibles;
    private int asientosDisponibles;
    private LocalDateTime fechaHora;
    private boolean eliminado = false;

    public Vuelo(int codigo, String origen, String destino, LocalDateTime fechaHora,
            int asientosTotales, int asientosTurista) {
        this.codigo = codigo;
        this.origen = origen;
        this.destino = destino;
        this.asientosTotales = asientosTotales;
        this.asientosDisponibles = asientosTotales;
        this.asientosTurista = asientosTurista;
        this.asientosTuristaDisponibles = asientosTurista;
        this.fechaHora = fechaHora;
    }

    public Vuelo(int codigo, String origen, String destino, LocalDateTime fechaHora,
            int asientosTotales, int asientosTurista, int asientosDisponibles,
            int asientosTuristaDisponibles) {
        this.codigo = codigo;
        this.origen = origen;
        this.destino = destino;
        this.asientosTotales = asientosTotales;
        this.asientosDisponibles = asientosTotales;
        this.asientosTurista = asientosTurista;
        this.asientosTuristaDisponibles = asientosTurista;
        this.fechaHora = fechaHora;
    }
    
    @Override
    public String toString() {
        return "Código: " + codigo + " | Origen: " + origen 
                + " | Destino: " + destino + " | Fecha Hora: " + fechaHora
                + " | Asientos Totales: " + asientosTotales 
                + " | Asientos Primera Disponibles: " + (asientosDisponibles - asientosTuristaDisponibles)
                + " | Asientos Turista Disponibles: " + asientosTuristaDisponibles;
    }

    public void eliminar() {
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
        return fechaHora;
    }

    public int getAsientosTotales() {
        return asientosTotales;
    }

    public int getAsientosTurista() {
        return asientosTurista;
    }
    
    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public int getAsientosPrimera() {
        return asientosTotales - asientosTurista;
    }

    public int getAsientosTuristaDisponibles() {
        return asientosTuristaDisponibles;
    }

    public int getAsientosPrimeraDisponibles() {
        return asientosTuristaDisponibles - asientosDisponibles;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    public void setAsientosDisponibles(int asientosDisponibles) {
        this.asientosDisponibles = asientosDisponibles;
    }
    
    public void setAsientosTuristaDisponibles(int asientosTuristaDisponibles) {
        this.asientosTuristaDisponibles = asientosTuristaDisponibles;
    }

}
