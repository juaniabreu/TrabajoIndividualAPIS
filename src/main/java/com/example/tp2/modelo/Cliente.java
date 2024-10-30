package com.example.tp2.modelo;

import java.util.ArrayList;
import java.util.List;

import com.example.tp2.exceptions.CuentaException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int numero;
	private String nombre;
	private String documento;
	@ManyToMany(mappedBy = "clientes",fetch = FetchType.EAGER)
	@JsonIgnoreProperties("clientes")
	private List<Cuenta> cuentas;

	public Cliente() {}
	public Cliente(String nombre, String documento) {
		this.nombre = nombre;
		this.documento = documento;
		this.cuentas = new ArrayList<Cuenta>();
	}

	public float saldoEnCuenta(String nroCuenta) throws CuentaException {
		for(Cuenta c : cuentas)
			if(c.soyLaCuenta(nroCuenta))
				return c.obtenerSaldo();
	    throw new CuentaException("No existe esa cuenta para ese cliente");
	}
	
	public float posicion() {
		float resultado = 0;
		for(Cuenta c : cuentas)
			resultado += c.obtenerSaldo();
		return resultado;
	}
	
	public List<Movimiento> movimientosMes(int mes, String nroCuenta) throws CuentaException{
		for(Cuenta c : cuentas)
			if(c.soyLaCuenta(nroCuenta)) {
				return c.movimientosDelMes(mes);
			}
		throw new CuentaException("No existe esa cuenta para ese cliente");
	}
	
	public int getNumero() {
		return numero;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDocumento() {
		return documento;
	}
	
	public boolean tieneDocumento(String documento) {
		return this.documento.equalsIgnoreCase(documento);
	}

	public boolean tieneNumero(int numero) {
		return this.numero == numero;
	}

	public void agregarCuenta(Cuenta cuenta) {
		cuentas.add(cuenta);
		cuenta.agregarClienteCuenta(this);
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	@Override
	public String toString() {
		return "Cliente{" +
				"numero=" + numero +
				", nombre='" + nombre + '\'' +
				", documento='" + documento + '\'' +
				", cuentas=" + cuentas +
				'}';
	}
}
