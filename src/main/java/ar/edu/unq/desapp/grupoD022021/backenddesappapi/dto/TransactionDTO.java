package ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.User;

import javax.persistence.ManyToOne;
import java.util.Date;

public class TransactionDTO {
    int id;
    Date hour;
    String cryptoactive;
    int cantidad;
    String usuarioVendedor;
    int idUserVendedor;
    String usuarioComprador;
    int idUserComprador;
    Boolean isFinalished=false;

    public TransactionDTO(int id, Date hour, String cryptoactive, int cantidad, String usuarioVendedor, int idUserVendedor, String usuarioComprador, int idUserComprador, Boolean isFinalished) {
        this.id = id;
        this.hour = hour;
        this.cryptoactive = cryptoactive;
        this.cantidad = cantidad;
        this.usuarioVendedor = usuarioVendedor;
        this.idUserVendedor = idUserVendedor;
        this.usuarioComprador = usuarioComprador;
        this.idUserComprador = idUserComprador;
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

    public Date getHour() {
        return hour;
    }

    public void setHour(Date hour) {
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

    public int getIdUserVendedor() {
        return idUserVendedor;
    }

    public void setIdUserVendedor(int idUserVendedor) {
        this.idUserVendedor = idUserVendedor;
    }

    public int getIdUserComprador() {
        return idUserComprador;
    }

    public void setIdUserComprador(int idUserComprador) {
        this.idUserComprador = idUserComprador;
    }

    public void setFinalished(Boolean finalished) {
        isFinalished = finalished;
    }
}
