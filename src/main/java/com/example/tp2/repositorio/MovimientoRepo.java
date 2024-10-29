package com.example.tp2.repositorio;

import com.example.tp2.modelo.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoRepo extends JpaRepository<Movimiento, Integer> {
}
