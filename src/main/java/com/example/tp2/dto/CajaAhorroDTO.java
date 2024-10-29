package com.example.tp2.dto;

import com.example.tp2.modelo.CajaAhorro;

public class CajaAhorroDTO {
    private String nro_cuenta;
    private float saldo;
    private float tasa_interes;

    public CajaAhorroDTO() {}
    public CajaAhorroDTO(CajaAhorro caja) {
        this.nro_cuenta=caja.getNroCuenta();
        this.saldo=caja.getSaldo();
        this.tasa_interes=caja.getTasaInteres();
    }

    public String getNro_cuenta() {
        return nro_cuenta;
    }

    public void setNro_cuenta(String nro_cuenta) {
        this.nro_cuenta = nro_cuenta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getTasa_interes() {
        return tasa_interes;
    }

    public void setTasa_interes(float tasa_interes) {
        this.tasa_interes = tasa_interes;
    }
}
