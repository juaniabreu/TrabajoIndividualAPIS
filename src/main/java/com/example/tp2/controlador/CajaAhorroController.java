package com.example.tp2.controlador;

import com.example.tp2.dao.CajaAhorroDAO;
import com.example.tp2.dao.ClienteDAO;
import com.example.tp2.dao.NumeroDAO;
import com.example.tp2.exceptions.CuentaException;
import com.example.tp2.modelo.CajaAhorro;
import com.example.tp2.modelo.Cliente;
import com.example.tp2.modelo.Movimiento;
import com.example.tp2.modelo.Numero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
        cajaAhorroDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}/depositar")
    public ResponseEntity<String> extraer(@PathVariable String id, @RequestParam int cantidad) throws CuentaException {
        Optional<CajaAhorro> caOpt = cajaAhorroDAO.findById(id);
        if (caOpt.isPresent()) {
            CajaAhorro ca = caOpt.get();
            if(ca.getSaldo() - cantidad >= 0){
            ca.extraer(cantidad);
            cajaAhorroDAO.save(ca);
            return new ResponseEntity<>("Extraccion exitosa",HttpStatus.OK);
            }
            return new ResponseEntity<>("Saldo Insuficiente",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("No se pudo encontrar la cuenta",HttpStatus.NOT_FOUND);
    }
    @PutMapping("agregarCliente/{id}")
    public ResponseEntity<CajaAhorro> agregarClienteACuenta(@PathVariable String id, @RequestBody Cliente cliente) throws CuentaException {
        Optional<CajaAhorro> caOpt = cajaAhorroDAO.findById(id);
        if (caOpt.isPresent()) {
            CajaAhorro ca = caOpt.get();
            ca.agregarClienteCuenta(cliente);
            cajaAhorroDAO.save(ca);
            return new ResponseEntity<>(ca, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}/depositar")
    public ResponseEntity<CajaAhorro> depositar(@PathVariable String id, @RequestParam int cantidad) throws CuentaException {
        Optional<CajaAhorro> caOpt = cajaAhorroDAO.findById(id);
        if (caOpt.isPresent()) {
            CajaAhorro ca = caOpt.get();
            ca.depositar(cantidad);
            cajaAhorroDAO.save(ca);
        return new ResponseEntity<>(ca, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/obtenerSaldo/{id}")
    public ResponseEntity<Float> obtenerSaldo(@PathVariable String id){
        Optional<CajaAhorro> caOpt = cajaAhorroDAO.findById(id);
        if (caOpt.isPresent()) {
            float x = caOpt.get().getSaldo();
            return new ResponseEntity<>(x, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/verExtracciones/{id}")
    public ResponseEntity<List<Movimiento>> verExtraccionesEntreFechas(@PathVariable String id,
                            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-mm-dd")Date fechaDesde,
                            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-mm-dd") Date fechaHasta){
        Optional<CajaAhorro> caOpt = cajaAhorroDAO.findById(id);
        if (caOpt.isPresent()) {
            CajaAhorro ca = caOpt.get();
            List<Movimiento> movimientos = ca.verExtraccionesEntreFechas(fechaDesde, fechaHasta);
            return new ResponseEntity<>(movimientos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/verDepositos/{id}")
    public ResponseEntity<List<Movimiento>> verDepositoEntreFechas(@PathVariable String id,
                                                                       @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-mm-dd")Date fechaDesde,
                                                                       @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-mm-dd") Date fechaHasta){
        Optional<CajaAhorro> caOpt = cajaAhorroDAO.findById(id);
        if (caOpt.isPresent()) {
            CajaAhorro ca = caOpt.get();
            List<Movimiento> movimientos = ca.verDepositosEntreFechas(fechaDesde, fechaHasta);
            return new ResponseEntity<>(movimientos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Movimiento>> verMovimientosporMes(@PathVariable String id, @RequestParam int mes){
        Optional<CajaAhorro> caOpt = cajaAhorroDAO.findById(id);
        if(caOpt.isPresent()){
            List<Movimiento> movimientos = caOpt.get().movimientosDelMes(mes);
            return new ResponseEntity<>(movimientos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
