package com.example.tp2.dao;

import com.example.tp2.modelo.CajaAhorro;
import com.example.tp2.modelo.CuentaCorriente;
import com.example.tp2.repositorio.CajaAhorroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CajaAhorroDAO {
    @Autowired
    private CajaAhorroRepo cajaAhorroRepo;

    public Optional<CajaAhorro> findById(String id) {
        return cajaAhorroRepo.findById("ca_"+id);
    }
    public List<CajaAhorro> findAll() {
        return cajaAhorroRepo.findAll();
    }
    public CajaAhorro save(CajaAhorro cajaAhorro) {
        return cajaAhorroRepo.save(cajaAhorro);
    }
    public void deleteById(String id) {
        cajaAhorroRepo.deleteById("ca_"+id);
    }

}
