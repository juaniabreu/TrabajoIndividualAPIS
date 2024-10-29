package com.example.tp2.repositorio;

import com.example.tp2.modelo.CuentaCorriente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaCorrienteRepo extends JpaRepository<CuentaCorriente,String> {
}
