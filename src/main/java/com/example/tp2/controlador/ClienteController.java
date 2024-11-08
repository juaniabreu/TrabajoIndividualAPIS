package com.example.tp2.controlador;

import com.example.tp2.dao.ClienteDAO;
import com.example.tp2.modelo.Cliente;
import com.example.tp2.repositorio.ClienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteDAO clienteDAO;

    @GetMapping("/")
    public String index() {
        return "Hello World clientes";
    }
    @GetMapping("/obtenerClientes")
    public ResponseEntity<List<Cliente>> obtenerClientes() {
       List<Cliente> clientes = clienteDAO.findAll();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }
    @GetMapping("/{documento}")//AGREGAR MESAJE POR SI NO LO ENCUENTRA
    public ResponseEntity<Cliente> obtenerClienteporDocumento(@PathVariable String documento) {
        Cliente cliente = clienteDAO.findClientByDocumento(documento);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }
    @PostMapping("/crear")
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        clienteDAO.save(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable String id) {
        clienteDAO.deleteByDocumento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
