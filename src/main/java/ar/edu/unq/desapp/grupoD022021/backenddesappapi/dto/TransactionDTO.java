package ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto;

import java.util.Date;

public class TransactionDTO {
    int id;
    String hour;
    String cryptoactive;
    int cantidad;
    String usuarioVendedor;
    String emailUserVendedor;
    String usuarioComprador;
    String emailUserComprador;
    Boolean isFinalished=false;

    public TransactionDTO(int id, String hour, String cryptoactive, int cantidad, String usuarioVendedor, String emailUserVendedor, String usuarioComprador, String emailUserComprador, Boolean isFinalished) {
        this.id = id;
        this.hour = hour;
        this.cryptoactive = cryptoactive;
        this.cantidad = cantidad;
        this.usuarioVendedor = usuarioVendedor;
        this.emailUserVendedor = emailUserVendedor;
        this.usuarioComprador = usuarioComprador;
        this.emailUserComprador = emailUserComprador;
        this.isFinalished = isFinalished;
    }

    public TransactionDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getCryptoactive() {
        return cryptoactive;
    }

    public void setCryptoactive(String cryptoactive) {
        this.cryptoactive = cryptoactive;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUsuarioVendedor() {
        return usuarioVendedor;
    }

    public void setUsuarioVendedor(String usuarioVendedor) {
        this.usuarioVendedor = usuarioVendedor;
    }

    public String getUsuarioComprador() {
        return usuarioComprador;
    }

    public void setUsuarioComprador(String usuarioComprador) {
        this.usuarioComprador = usuarioComprador;
    }

    public Boolean getFinalished() {
        return isFinalished;
    }

    public String getEmailUserVendedor() {
        return emailUserVendedor;
    }

    public void setEmailUserVendedor(String emailUserVendedor) {
        this.emailUserVendedor = emailUserVendedor;
    }

    public String getEmailUserComprador() {
        return emailUserComprador;
    }

    public void setEmailUserComprador(String emailUserComprador) {
        this.emailUserComprador = emailUserComprador;
    }

    public void setFinalished(Boolean finalished) {
        isFinalished = finalished;
    }
}
