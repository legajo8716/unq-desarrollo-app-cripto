package ar.edu.unq.desapp.grupoD022021.backenddesappapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Cryptoactive implements Serializable {


    private Integer id;

    private String symbol;
    @JsonProperty("price")
    private Double price;


    private Double priceAr;
    private String quoteTime;

    public Cryptoactive(String symbol, String price) {
        this.symbol = symbol;
        this.price = Double.parseDouble(price);
        this.priceAr = null;
        this.quoteTime = null;
    }

    public Cryptoactive(){}


    public String getSymbol() {
        return symbol;
    }

    public Double getPrice(){ return price; }

    public Double getPriceAr() {
        return priceAr;
    }

    public void setPriceAr(Double priceAr) {
        this.priceAr = priceAr;
    }

    public String getQuoteTime() {
        return quoteTime;
    }

    public void setQuoteTime() {
        /** Anulo el argumento que recibo, xque la cotizacion es en base a la hora que me pidan el listado de cryptos**/
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.quoteTime = dtf.format(now);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cryptoactive that = (Cryptoactive) o;
        return id.equals(that.id) && symbol.equals(that.symbol) && price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, symbol, price);
    }

    @Override
    public String toString() {
        return "Cryptoactive{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", price=" + price +
                '}';
    }
}
