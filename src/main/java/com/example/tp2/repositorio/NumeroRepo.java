package com.example.tp2.repositorio;

import com.example.tp2.modelo.Numero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NumeroRepo extends JpaRepository<Numero,Integer> {
}
