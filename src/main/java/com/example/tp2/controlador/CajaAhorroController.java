package com.example.tp2.controlador;

import com.example.tp2.dao.CajaAhorroDAO;
import com.example.tp2.dao.ClienteDAO;
import com.example.tp2.dao.NumeroDAO;
import com.example.tp2.modelo.CajaAhorro;
import com.example.tp2.modelo.Cliente;
import com.example.tp2.modelo.Numero;
import com.example.tp2.repositorio.CajaAhorroRepo;
import com.example.tp2.repositorio.ClienteRepo;
import com.example.tp2.repositorio.NumeroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ca")
public class CajaAhorroController {

    @Autowired
    CajaAhorroDAO cajaAhorroDAO;
    @Autowired
    NumeroDAO numeroDAO;
    @Autowired
    private ClienteDAO clienteDAO;

    @GetMapping()
    public String index() {
        return "Caja Ahorro";
    }

    @GetMapping("/obtenerCA")
    public ResponseEntity<List<CajaAhorro>> obtenerCajasAhorro() {
        List<CajaAhorro> cajaAhorroList = cajaAhorroDAO.findAll();
        return new ResponseEntity<>(cajaAhorroList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CajaAhorro> obtenerCajaAhorro(@PathVariable String id) {
        Optional<CajaAhorro> ca = cajaAhorroDAO.findById(id);
        return ca.map(c -> new ResponseEntity<>(c, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //AGREGAR LAS FUNCIONES DE CADA CLASE
    @PostMapping("/{documento}")
    public ResponseEntity<CajaAhorro> crearCajaAhorro(@PathVariable String documento){
        Cliente cliente = clienteDAO.findClientByDocumento(documento);
        if(cliente == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Numero numero = numeroDAO.findById(1).get();
        int x = numero.getNumero_ca() + 1;
        CajaAhorro newCaja = new CajaAhorro(cliente);
        newCaja.setNroCuenta("ca_"  + x);
        cajaAhorroDAO.save(newCaja);
        numero.setNumero_ca(x);
        numeroDAO.save(numero);
        return new ResponseEntity<>(newCaja, HttpStatus.CREATED);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCajaAhorro(@PathVariable String id){
        cajaAhorroDAO.deleteById("ca_"+id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
