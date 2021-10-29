package ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.User;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class ActivityDto  {

        int id;
        int hour;
        String cryptoactive;
        int cantidad;
        String usuario;
        int idUsuario;

        public ActivityDto() {
        }

    public ActivityDto(int id, int hour, String cryptoactive, int cantidad, String usuario) {
        this.id = id;
        this.hour = hour;
        this.cryptoactive = cryptoactive;
        this.cantidad = cantidad;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
