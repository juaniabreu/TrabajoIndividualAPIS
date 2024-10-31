package com.example.tp2.dao;

import com.example.tp2.repositorio.CuentaCorrienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CuentaCorrienteDAO {
    @Autowired
    CuentaCorrienteRepo cuentaCorrienteRepo;
}
