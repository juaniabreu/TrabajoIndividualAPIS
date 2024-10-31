package com.example.tp2.dao;

import com.example.tp2.modelo.Cliente;
import com.example.tp2.repositorio.ClienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteDAO {
    @Autowired
    private ClienteRepo clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
    public void deleteByDocumento(String documento) {
        Cliente c  = clienteRepository.findAll()
                .stream()
                .filter(cliente -> cliente.getDocumento().equalsIgnoreCase(documento)).findFirst().orElse(null);
        clienteRepository.deleteById(c.getNumero());
    }
    public Cliente save(Cliente cliente){
        return clienteRepository.save(cliente);
    }
    public Cliente findClientByDocumento(String documento) {
        return clienteRepository.findAll()
                .stream()
                .filter(cliente -> cliente.getDocumento().equalsIgnoreCase(documento)).findFirst().orElse(null);
    }
}
