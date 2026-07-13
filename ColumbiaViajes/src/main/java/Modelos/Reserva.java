
package Modelos;

import Enums.Hospedaje;
import Enums.Clase;
import java.time.LocalDateTime;

public class Reserva{
    private final int codigo;
    private int codTurista;
    private int codVendedor;
    private int codSucursal;
    private Integer codVuelo;
    private Integer codHotel;
    private Hospedaje  hospedaje;
    private Clase clase;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private boolean eliminado; 

    public Reserva(int codigo, int codTurista, int codVendedor, int codSucursal) {
        this.codigo = codigo;
        this.codTurista = codTurista;
        this.codVendedor = codVendedor;
        this.codSucursal = codSucursal;
    }
    
    public void eliminar() {
        this.eliminado = true;
    }
    
    public boolean isEliminado() {
        return eliminado;
    }
        
    public int getCodTurista() {
        return codTurista;
    }

    public int getCodVendedor() {
        return codVendedor;
    }

    public Integer getCodVuelo() {
        return codVuelo;
    }

    public Integer getCodHotel() {
        return codHotel;
    }

    public int getCodigo() {
        return codigo;
    }
    
    public int getCodSucursal() {
        return codSucursal;
    }
    
    public Hospedaje getHospedaje() {
        return hospedaje;
    }

    public Clase getClase() {
        return clase;
    }
    
    public LocalDateTime getCheckIn(){
        return checkIn;
    }
    
    public LocalDateTime getCheckOut(){
        return checkOut;
    }

    public void setCodTurista(int codTurista) {
        this.codTurista = codTurista;
    }

    public void setCodVendedor(int codVendedor) {
        this.codVendedor = codVendedor;
    }

    public void setCodVuelo(int codVuelo) {
        this.codVuelo = codVuelo;
    }

    public void setCodHotel(int codHotel) {
        this.codHotel = codHotel;
    }
    
    public void setCodSucursal(int codSucursal) {
        this.codSucursal = codSucursal;
    }
    
    public void setHospedaje(Hospedaje hospedaje) {
        this.hospedaje = hospedaje;
    }
    
    public void agregarVuelo(Integer codVuelo, Clase clase) {
        this.codVuelo = codVuelo;
        this.clase = clase;
    }
    
    public void agregarHotel(Integer codHotel, Hospedaje hospedaje, 
            LocalDateTime checkIn, LocalDateTime checkOut) {
        this.codHotel = codHotel;
        this.hospedaje = hospedaje;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
   
}
