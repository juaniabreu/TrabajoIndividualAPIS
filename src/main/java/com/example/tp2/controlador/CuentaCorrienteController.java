package com.example.tp2.controlador;

import com.example.tp2.dto.CajaAhorroDTO;
import com.example.tp2.dto.CuentaCorrienteDTO;
import com.example.tp2.modelo.CajaAhorro;
import com.example.tp2.modelo.CuentaCorriente;
import com.example.tp2.repositorio.CajaAhorroRepo;
import com.example.tp2.repositorio.CuentaCorrienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cc")
public class CuentaCorrienteController {

    @Autowired
    CuentaCorrienteRepo ccRepo;;
    @GetMapping()
    public String index(){
        return "Cuenta Corriente";
    }
    @GetMapping("/obtenerCC")
    public ResponseEntity<List<CuentaCorrienteDTO>> obtenerCuentasCorriente(){
        List<CuentaCorriente> cuentaCorrientes = ccRepo.findAll();
        List<CuentaCorrienteDTO> dtos = new ArrayList<>();
        for (CuentaCorriente cuentacorriente : cuentaCorrientes) {
            CuentaCorrienteDTO dto = new CuentaCorrienteDTO(cuentacorriente);
            dtos.add(dto);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CuentaCorriente> obtenerCuentaCorriente(@PathVariable String id){
        Optional<CuentaCorriente> cc = ccRepo.findById("cc_"+id);
        return cc.map(c -> new ResponseEntity<>(c,HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping()
    public ResponseEntity<CuentaCorriente> crearCuentaCorriente(@RequestBody CuentaCorriente cuentaCorriente){
        ccRepo.save(cuentaCorriente);
        return new ResponseEntity<>(cuentaCorriente, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuentaCorriente(@PathVariable String id){
        ccRepo.deleteById("cc_"+id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
