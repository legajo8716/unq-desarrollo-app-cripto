package ar.edu.unq.desapp.grupoD022021.backenddesappapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Table(name = "cryptoassets")
public class Cryptoactive {

    @Id
    private Integer id;
    @Column
    private String symbol;
    @Column
    @JsonProperty("price")
    private Double price;


    @Column
    private Double priceAr;
    @Column
    private String quoteTime;

    public Cryptoactive(Integer id, String symbol, String price) {
        this.id = id;
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

    public void setQuoteTime(LocalDate quoteTime) {
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
