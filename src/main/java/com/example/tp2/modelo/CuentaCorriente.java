package com.example.tp2.modelo;

import java.util.Calendar;

import com.example.tp2.exceptions.CuentaException;
import jakarta.persistence.Entity;

@Entity
public class CuentaCorriente extends Cuenta {

	private float descubiertoHabilitado;
	private float costoMantenimiento;
	private float tasaDiariaDescubierto;

	public CuentaCorriente(Cliente cliente,float descubiertoHabilitado, float costoMantenimiento, float tasaDiariaDescubierto) {
		super(cliente);
		this.descubiertoHabilitado = descubiertoHabilitado;
		this.costoMantenimiento = costoMantenimiento;
		this.tasaDiariaDescubierto = tasaDiariaDescubierto;
	}
	public CuentaCorriente(Cliente cliente,float descubiertoHabilitado, float costoMantenimiento, float tasaDiariaDescubierto, int numero) {
		super(cliente);
		this.nroCuenta = "cc_"+numero;
		this.descubiertoHabilitado = descubiertoHabilitado;
		this.costoMantenimiento = costoMantenimiento;
		this.tasaDiariaDescubierto = tasaDiariaDescubierto;
	}

	public CuentaCorriente() {

	}

	@Override
	public int extraer(float importe) throws CuentaException {
		if(this.saldo + this.descubiertoHabilitado > importe) {
			this.saldo -= importe;
			Movimiento movimiento = new Movimiento(Calendar.getInstance().getTime(), "Extraccion", importe);
			this.movimientos.add(movimiento);
			return movimiento.getNroMovimiento();
		}
		else
			throw new CuentaException("El saldo es insuficiente");
	}

	@Override
	public float disponible() {
		return this.saldo + this.descubiertoHabilitado;
	}

	public float getDescubiertoHabilitado() {
		return descubiertoHabilitado;
	}

	public float getCostoMantenimiento() {
		return costoMantenimiento;
	}

	public float getTasaDiariaDescubierto() {
		return tasaDiariaDescubierto;
	}

}
