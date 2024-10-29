package com.example.tp2.dto;

import com.example.tp2.modelo.CuentaCorriente;

public class CuentaCorrienteDTO {
    private String nro_cuenta;
    private float saldo;
    private float descubiertoHabilitado;
    private float costoMantenimiento;
    private float tasaDiariaDescubierto;

    public CuentaCorrienteDTO() {
    }
    public CuentaCorrienteDTO(CuentaCorriente cuentaCorriente) {
        this.nro_cuenta = cuentaCorriente.getNroCuenta();
        this.saldo = cuentaCorriente.getSaldo();
        this.descubiertoHabilitado = cuentaCorriente.getDescubiertoHabilitado();
        this.costoMantenimiento = cuentaCorriente.getCostoMantenimiento();
        this.tasaDiariaDescubierto = cuentaCorriente.getTasaDiariaDescubierto();
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

    public float getDescubiertoHabilitado() {
        return descubiertoHabilitado;
    }

    public void setDescubiertoHabilitado(float descubiertoHabilitado) {
        this.descubiertoHabilitado = descubiertoHabilitado;
    }

    public float getCostoMantenimiento() {
        return costoMantenimiento;
    }

    public void setCostoMantenimiento(float costoMantenimiento) {
        this.costoMantenimiento = costoMantenimiento;
    }

    public float getTasaDiariaDescubierto() {
        return tasaDiariaDescubierto;
    }

    public void setTasaDiariaDescubierto(float tasaDiariaDescubierto) {
        this.tasaDiariaDescubierto = tasaDiariaDescubierto;
    }
}
