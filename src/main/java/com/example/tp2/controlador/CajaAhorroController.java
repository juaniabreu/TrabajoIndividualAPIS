package com.example.tp2.controlador;

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
    ClienteRepo clienteRepo;
    @Autowired
    CajaAhorroRepo caRepo;
    @Autowired
    NumeroRepo numeroRepo;

    @GetMapping()
    public String index() {
        return "Caja Ahorro";
    }

    @GetMapping("/obtenerCA")
    public ResponseEntity<List<CajaAhorro>> obtenerCajasAhorro() {
        List<CajaAhorro> cajaAhorroList = caRepo.findAll();
        return new ResponseEntity<>(cajaAhorroList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CajaAhorro> obtenerCajaAhorro(@PathVariable String id) {
        Optional<CajaAhorro> ca = caRepo.findById("ca_" + id);
        return ca.map(c -> new ResponseEntity<>(c, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //AGREGAR LAS FUNCIONES DE CADA CLASE
    /*@PostMapping()
    public ResponseEntity<CajaAhorro> crearCajaAhorro(@RequestBody String documento){

        Numero numero = numeroRepo.findById(1).get();
        int x = numero.getNumero_ca() + 1;
        CajaAhorro newCaja = new CajaAhorro(cliente);
        newCaja.setNroCuenta("ca_"  + x);
        caRepo.save(newCaja);
        numero.setNumero_ca(x);
        numeroRepo.save(numero);
        return new ResponseEntity<>(newCaja, HttpStatus.CREATED);
    }

     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCajaAhorro(@PathVariable String id){
        caRepo.deleteById("ca_"+id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
