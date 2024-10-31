package com.example.tp2.controlador;

import com.example.tp2.dao.ClienteDAO;
import com.example.tp2.dao.CuentaCorrienteDAO;
import com.example.tp2.dao.NumeroDAO;
import com.example.tp2.modelo.Cliente;
import com.example.tp2.modelo.CuentaCorriente;
import com.example.tp2.modelo.Numero;
import com.example.tp2.repositorio.ClienteRepo;
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

    //AGREGAR FUNCIONES DE LA FUNCION
    //Y QUE HACER CON LOS PARAMETROS, ESTABLECER DEFAULTS?O MANEJAR INGRESO DE DATOS?
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
}
