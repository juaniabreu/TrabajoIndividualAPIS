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
    ClienteRepo clienteRepo;
    @Autowired
    private ClienteDAO clienteDAO;

    @GetMapping("/")
    public String index() {
        return "Hello World clientes";
    }
    @GetMapping("/obtenerClientes")
    public ResponseEntity<List<Cliente>> obtenerClientes() {
       List<Cliente> clientes = clienteRepo.findAll();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }
    @GetMapping("/clientexdocumento/{documento}")
    public ResponseEntity<Cliente> obtenerClienteporDocumento(@PathVariable String documento) {
        Cliente cliente = clienteDAO.findClientByDocumento(documento);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable int id) {
        Optional<Cliente> cliente = clienteRepo.findById(id);
        return cliente.map(c -> new ResponseEntity<>(c, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping()
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        clienteRepo.save(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> eliminarCliente(@PathVariable int id) {
        clienteRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
