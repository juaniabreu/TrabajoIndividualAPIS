package com.example.tp2;

import com.example.tp2.modelo.CajaAhorro;
import com.example.tp2.modelo.Cliente;
import com.example.tp2.modelo.CuentaCorriente;
import com.example.tp2.modelo.Numero;
import com.example.tp2.repositorio.CajaAhorroRepo;
import com.example.tp2.repositorio.ClienteRepo;
import com.example.tp2.repositorio.NumeroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class Tp2Application implements CommandLineRunner {

    @Autowired NumeroRepo numeroRepo;

    public static void main(String[] args) {
        SpringApplication.run(Tp2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if(numeroRepo.findById(1).isEmpty()) {
            Numero numero = new Numero();
            numeroRepo.save(numero);
        }
    }
}
