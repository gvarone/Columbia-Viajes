
package Modelos;

import Enums.Hospedaje;
import Enums.Clase;
import java.io.Serializable;
import java.time.LocalDate;

public class Paquete implements Serializable {
    private final int codigo;
    private int codTurista;
    private int codVendedor;
    private int codSucursal;
    private Integer codVuelo;
    private Integer codHotel;
    private Hospedaje  hospedaje;
    private Clase clase;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public Paquete(int codTurista, int codVendedor, int codSucursal, int codigo) {
        this.codigo = codigo;
        this.codTurista = codTurista;
        this.codVendedor = codVendedor;
        this.codSucursal = codSucursal;
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

    public void setClase(Clase clase) {
        this.clase = clase;
    }
    
    public void agregarVuelo(Integer codVuelo, Clase clase) {
        this.codVuelo = codVuelo;
        this.clase = clase;
    }
    
    public void agregarHotel(Integer codHotel, Hospedaje hospedaje, LocalDate checkIn, LocalDate checkOut) {
        this.codHotel = codHotel;
        this.hospedaje = hospedaje;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
   
}
