package ar.edu.unq.desapp.grupoD022021.backenddesappapi.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        int id;
        Date hour;
        String cryptoactive;
        int cantidad;
        String usuarioVendedor;
        String usuarioComprador;
        Boolean isFinalished=false;

    public Boolean getFinalished() {
        return isFinalished;
    }

    public void setFinalished(Boolean finalished) {
        isFinalished = finalished;
    }

    public Transaction(){

        }

    public Date getHour() {
        return hour;
    }

    public String getCryptoactive() {
        return cryptoactive;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getUsuarioVendedor() {
        return usuarioVendedor;
    }

    public String getUsuarioComprador() {
        return usuarioComprador;
    }


    public void setHour(Date hour) {
        this.hour = hour;
    }

    public void setCryptoactive(String cryptoactive) {
        this.cryptoactive = cryptoactive;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setUsuarioVendedor(String usuarioVendedor) {
        this.usuarioVendedor = usuarioVendedor;
    }

    public void setUsuarioComprador(String usuarioComprador) {
        this.usuarioComprador = usuarioComprador;
    }
}
