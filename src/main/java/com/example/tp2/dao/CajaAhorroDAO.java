package com.example.tp2.dao;

import com.example.tp2.repositorio.CajaAhorroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CajaAhorroDAO {
    @Autowired
    private CajaAhorroRepo cajaAhorroRepo;


}
