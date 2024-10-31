package com.example.tp2.dao;

import com.example.tp2.modelo.CuentaCorriente;
import com.example.tp2.modelo.Numero;
import com.example.tp2.repositorio.NumeroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class NumeroDAO {
    @Autowired
    private NumeroRepo numeroRepo;
    public Optional<Numero> findById(int id) {
        return numeroRepo.findById(id);
    }
    public Numero save(Numero numero) {
        return numeroRepo.save(numero);
    }
}
