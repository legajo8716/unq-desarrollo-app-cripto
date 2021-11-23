package ar.edu.unq.desapp.grupoD022021.backenddesappapi.model;


import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        int id;
        String hour;
        String cryptoactive;
        int cantidad;
        @ManyToOne
        User usuarioVendedor;
        @ManyToOne
        User usuarioComprador;
        Boolean isFinalished=false;
        String shippingAddress;
        int reputation;

    public Boolean getFinalished() {
        return isFinalished;
    }

    public void setFinalished(Boolean finalished) {
        isFinalished = finalished;
    }

    public Transaction(){

        }

    public String getHour() {
        return hour;
    }

    public String getCryptoactive() {
        return cryptoactive;
    }

    public int getCantidad() {
        return cantidad;
    }

    public User getUsuarioVendedor() {
        return usuarioVendedor;
    }

    public User getUsuarioComprador() {
        return usuarioComprador;
    }


    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setCryptoactive(String cryptoactive) {
        this.cryptoactive = cryptoactive;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setUsuarioVendedor(User usuarioVendedor) {
        this.usuarioVendedor = usuarioVendedor;
    }

    public void setUsuarioComprador(User usuarioComprador) {
        this.usuarioComprador = usuarioComprador;
    }

    public void confirm() {
        this.isFinalished=true;
        sumTransactionConfirmed();

    }

    public void cancel(){

        this.isFinalished=true;
    }

    public void sumTransactionConfirmed() {
        this.usuarioComprador.sumTransactionConfirmed();
        this.usuarioVendedor.sumTransactionConfirmed();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String action) {
        if(action.length() == ("purchase").length()){
            this.shippingAddress = usuarioVendedor.getWallet();
        } else if(action.length() == ("sale").length()) {
            this.shippingAddress = usuarioVendedor.getCVU();
        }
    }

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }
}
