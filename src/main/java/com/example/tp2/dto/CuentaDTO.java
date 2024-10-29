package com.example.tp2.dto;

import com.example.tp2.modelo.Cuenta;

public class CuentaDTO {
    private String nro_cuenta;
    private float saldo;

    public CuentaDTO(Cuenta cuenta) {
        this.nro_cuenta = cuenta.getNroCuenta();
        this.saldo = cuenta.getSaldo();
    }
}

