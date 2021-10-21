package ar.edu.unq.desapp.grupoD022021.backenddesappapi.model;

import javax.persistence.*;
@Entity
@Table(name = "transaction")
public class Transaction {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        int id;
        int hour;
        Cryptoactive cryptoactive;
        int cantidad;
        User usuarioVendedor;
        User usuarioComprador;

    public Transaction(){

        }

    public Transaction(int hour, Cryptoactive cryptoactive, int cantidad, User usuarioVendedor) {
        this.hour = hour;
        this.cryptoactive = cryptoactive;
        this.cantidad = cantidad;
        this.usuarioVendedor = usuarioVendedor;
    }
}
