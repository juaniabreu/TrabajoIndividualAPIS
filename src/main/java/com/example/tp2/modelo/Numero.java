package com.example.tp2.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Numero {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private int numero_cc=0;
        private int numero_ca=0;

        public Numero() {}

    public Numero(int numero_cc, int numero_ca){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero_cc() {
        return numero_cc;
    }

    public void setNumero_cc(int numero_cc) {
        this.numero_cc = numero_cc;
    }

    public int getNumero_ca() {
        return numero_ca;
    }

    public void setNumero_ca(int numero_ca) {
        this.numero_ca = numero_ca;
    }
}
