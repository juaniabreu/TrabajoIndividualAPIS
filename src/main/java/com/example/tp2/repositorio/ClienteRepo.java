package com.example.tp2.repositorio;

import com.example.tp2.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepo extends JpaRepository<Cliente,Integer> {
}
