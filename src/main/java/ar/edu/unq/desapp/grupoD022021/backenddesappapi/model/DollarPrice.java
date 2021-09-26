package ar.edu.unq.desapp.grupoD022021.backenddesappapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

/** CLASE PARA PARSEAR EL JSON QUE ME VIENE DE LA API DEL BCRA**/
public class DollarPrice {

    @JsonProperty("d")
    private LocalDate date;
    @JsonProperty("v")
    private Double value;

    public DollarPrice(String date, Double value) {

        this.date = LocalDate.parse(date);
        this.value = value;
    }

    public DollarPrice() {   }

    public LocalDate getDate() {
        return date;
    }


    public Double getValue() {
        return value;
    }

}
