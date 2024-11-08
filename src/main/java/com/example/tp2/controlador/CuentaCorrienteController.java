package com.example.tp2.controlador;

import com.example.tp2.dao.ClienteDAO;
import com.example.tp2.dao.CuentaCorrienteDAO;
import com.example.tp2.dao.NumeroDAO;
import com.example.tp2.exceptions.CuentaException;
import com.example.tp2.modelo.*;
import com.example.tp2.repositorio.ClienteRepo;
import com.example.tp2.repositorio.CuentaCorrienteRepo;
import com.example.tp2.repositorio.NumeroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cc")
public class CuentaCorrienteController {

    @Autowired
    CuentaCorrienteDAO cuentaCorrienteDAO;
    @Autowired
    NumeroDAO numeroDAO;
    @Autowired
    private ClienteDAO clienteDAO;

    @GetMapping()
    public String index(){
        return "Cuenta Corriente";
    }
    @GetMapping("/obtenerCC")
    public ResponseEntity<List<CuentaCorriente>> obtenerCuentasCorriente(){
       List<CuentaCorriente> cuentaCorrientes = cuentaCorrienteDAO.findAll();
        return new ResponseEntity<>(cuentaCorrientes, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CuentaCorriente> obtenerCuentaCorriente(@PathVariable String id){
        Optional<CuentaCorriente> cc = cuentaCorrienteDAO.findById(id);
        return cc.map(c -> new ResponseEntity<>(c,HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{documento}")
    public ResponseEntity<CuentaCorriente> crearCuentaCorriente(@PathVariable String documento){
        Cliente cliente = clienteDAO.findClientByDocumento(documento);
        if(cliente == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Numero numero = numeroDAO.findById(1).get();
        int x = numero.getNumero_cc() + 1;
           CuentaCorriente newCC = new CuentaCorriente(cliente,1,1,1,x);
        newCC.setNroCuenta("cc_" + x);
        cuentaCorrienteDAO.save(newCC);
        numero.setNumero_cc(x);
        numeroDAO.save(numero);
        return new ResponseEntity<>(newCC, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuentaCorriente(@PathVariable String id){
        cuentaCorrienteDAO.deleteById("cc_"+id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("agregarCliente/{id}")
    public ResponseEntity<CuentaCorriente> agregarClienteACuenta(@PathVariable String id, @RequestBody Cliente cliente) throws CuentaException {
        Optional<CuentaCorriente> ccOpt = cuentaCorrienteDAO.findById(id);
        if (ccOpt.isPresent()) {
            CuentaCorriente cc = ccOpt.get();
            cc.agregarClienteCuenta(cliente);
            cuentaCorrienteDAO.save(cc);
            return new ResponseEntity<>(cc, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    //CAMBIAR METODOS PARA CUENTA CORRIENTE
    @PutMapping("/{id}/depositar")
    public ResponseEntity<CuentaCorriente> depositar(@PathVariable String id, @RequestParam int cantidad) throws CuentaException {
        Optional<CuentaCorriente> ccOpt = cuentaCorrienteDAO.findById(id);
        if (ccOpt.isPresent()) {
            CuentaCorriente cc = ccOpt.get();
            cc.depositar(cantidad);
            cuentaCorrienteDAO.save(cc);
            return new ResponseEntity<>(cc, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/obtenerSaldo/{id}")
    public ResponseEntity<Float> obtenerSaldo(@PathVariable String id){
        Optional<CuentaCorriente> ccOpt = cuentaCorrienteDAO.findById(id);
        if (ccOpt.isPresent()) {
            float x = ccOpt.get().getSaldo();
            return new ResponseEntity<>(x, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/verExtracciones/{id}")
    public ResponseEntity<List<Movimiento>> verExtraccionesEntreFechas(@PathVariable String id,
                                                                       @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-mm-dd") Date fechaDesde,
                                                                       @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-mm-dd") Date fechaHasta){
        Optional<CuentaCorriente> ccOpt = cuentaCorrienteDAO.findById(id);
        if (ccOpt.isPresent()) {
            CuentaCorriente cc = ccOpt.get();
            List<Movimiento> movimientos = cc.verExtraccionesEntreFechas(fechaDesde, fechaHasta);
            return new ResponseEntity<>(movimientos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/verDepositos/{id}")
    public ResponseEntity<List<Movimiento>> verDepositoEntreFechas(@PathVariable String id,
                                                                   @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-mm-dd")Date fechaDesde,
                                                                   @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-mm-dd") Date fechaHasta){
        Optional<CuentaCorriente> ccOpt = cuentaCorrienteDAO.findById(id);
        if (ccOpt.isPresent()) {
            CuentaCorriente cc = ccOpt.get();
            List<Movimiento> movimientos = cc.verDepositosEntreFechas(fechaDesde, fechaHasta);
            return new ResponseEntity<>(movimientos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Movimiento>> verMovimientosporMes(@PathVariable String id, @RequestParam int mes){
        Optional<CuentaCorriente> ccOpt = cuentaCorrienteDAO.findById(id);
        if(ccOpt.isPresent()){
            List<Movimiento> movimientos = ccOpt.get().movimientosDelMes(mes);
            return new ResponseEntity<>(movimientos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
