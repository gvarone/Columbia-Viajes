package Modelos;

import Enums.Rol;

public class Vendedor extends Usuario {

    private double totalFacturado;

    public Vendedor(int codigo, String nombre, String apellido, String contrasenia,
            String username) {
        super(codigo, nombre, apellido, contrasenia, Rol.VENDEDOR, username);
        this.totalFacturado = 0.0;
    }

    public double getTotalFacturado() {
        return this.totalFacturado;
    }

    public void sumarVenta(int monto) {
        this.totalFacturado += monto;
    }
}
