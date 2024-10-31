package com.example.tp2.controlador;

import com.example.tp2.modelo.Cliente;
import com.example.tp2.modelo.CuentaCorriente;
import com.example.tp2.modelo.Numero;
import com.example.tp2.repositorio.CuentaCorrienteRepo;
import com.example.tp2.repositorio.NumeroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cc")
public class CuentaCorrienteController {

    @Autowired
    CuentaCorrienteRepo ccRepo;
    @Autowired
    NumeroRepo numeroRepo;
    @GetMapping()
    public String index(){
        return "Cuenta Corriente";
    }
    @GetMapping("/obtenerCC")
    public ResponseEntity<List<CuentaCorriente>> obtenerCuentasCorriente(){
       List<CuentaCorriente> cuentaCorrientes = ccRepo.findAll();
        return new ResponseEntity<>(cuentaCorrientes, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CuentaCorriente> obtenerCuentaCorriente(@PathVariable String id){
        Optional<CuentaCorriente> cc = ccRepo.findById("cc_"+id);
        return cc.map(c -> new ResponseEntity<>(c,HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //CAMBIAR CLIENTE cliente por String Documento para buscar por documento
    @PostMapping()
    public ResponseEntity<CuentaCorriente> crearCuentaCorriente(@RequestBody Cliente cliente){
        Numero numero = numeroRepo.findById(1).get();
        int x = numero.getNumero_cc() + 1;
        CuentaCorriente newCC = new CuentaCorriente();
        newCC.setNroCuenta("cc_" + x);
        ccRepo.save(newCC);
        numero.setNumero_cc(x);
        numeroRepo.save(numero);
        return new ResponseEntity<>(newCC, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuentaCorriente(@PathVariable String id){
        ccRepo.deleteById("cc_"+id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
