package Modelos;

import Enums.Rol;

public class Vendedor extends Usuario {

    private double total_facturado;

    public Vendedor(int codigo, String nombre, String apellido, String contrasenia) {
        super(codigo, nombre, apellido, contrasenia, Rol.VENDEDOR);
        this.total_facturado = 0.0;
    }

    public double getTotalFacturado() {
        return this.total_facturado;
    }

    public void sumarVenta(int monto) {
        this.total_facturado += monto;
    }
}
