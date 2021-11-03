package ar.edu.unq.desapp.grupoD022021.backenddesappapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

/** CLASE PARA PARSEAR EL JSON QUE ME VIENE DE LA API DEL BCRA**/
public class DollarPrice {


    @JsonProperty("venta")
    private String value;

    public DollarPrice( String value) {

        this.value = value;
    }

    public DollarPrice() {   }


    public Double getValue() {
        return Double.parseDouble(value.replace(',', '.'));
    }

}
