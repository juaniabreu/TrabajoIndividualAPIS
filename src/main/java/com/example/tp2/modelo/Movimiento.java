package com.example.tp2.modelo;

import java.util.Date;

import com.example.tp2.views.MovimientoView;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movimiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int nroMovimiento;
	private Date fecha;
	private String tipoMovimiento;
	private float importe;
	
	public Movimiento(Date fecha, String tipoMovimiento, float importe) {
		this.fecha = fecha;
		this.tipoMovimiento = tipoMovimiento;
		this.importe = importe;
	}

	public Movimiento() {

	}

	public int getNroMovimiento() {
		return this.nroMovimiento;
	}
	public boolean soyDeEseMes(int mes) {
		 return this.fecha.getMonth() == mes;
	}
	
	public boolean soyDeposito() {
		return this.tipoMovimiento.equalsIgnoreCase("Deposito");
	}
	
	public float obtenerImporte() {
		return this.importe;
	}

	public MovimientoView toView() {
		return new MovimientoView(nroMovimiento,fecha, tipoMovimiento, importe);
	}
}
