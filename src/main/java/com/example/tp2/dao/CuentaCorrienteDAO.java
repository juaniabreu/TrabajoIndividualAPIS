package com.example.tp2.dao;

import com.example.tp2.modelo.CuentaCorriente;
import com.example.tp2.repositorio.CuentaCorrienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CuentaCorrienteDAO {
    @Autowired
    CuentaCorrienteRepo cuentaCorrienteRepo;

    public List<CuentaCorriente> findAll() {
        return cuentaCorrienteRepo.findAll();
    }
    public Optional<CuentaCorriente> findById(String id) {
        return cuentaCorrienteRepo.findById("cc_"+id);
    }
    public CuentaCorriente save(CuentaCorriente cuentaCorriente) {
        return cuentaCorrienteRepo.save(cuentaCorriente);
    }
    public void deleteById(String id) {
        cuentaCorrienteRepo.deleteById(id);
    }
}
