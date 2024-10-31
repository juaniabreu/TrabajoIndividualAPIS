package com.example.tp2.dao;

import com.example.tp2.modelo.Cliente;
import com.example.tp2.repositorio.ClienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClienteDAO {
    @Autowired
    private ClienteRepo clienteRepository;

    public Cliente findClientByDocumento(String documento) {
        return clienteRepository.findAll()
                .stream()
                .filter(cliente -> cliente.getDocumento().equalsIgnoreCase(documento)).findFirst().orElse(null);
    }
}
