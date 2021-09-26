package ar.edu.unq.desapp.grupoD022021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Cryptoactive;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.DollarPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class CriptoactiveController {

    @Autowired
    RestTemplate restTemplate;

    //TODO: Hacer de la lista de cryptos enums
    @RequestMapping("/api/cryptoassets")
    public List<Cryptoactive> getCryptoassets(){
        List<Cryptoactive> cryptoassets = new ArrayList<>();
        List<String> cryptos = new ArrayList<String>();
        cryptos.add("ALICEUSDT");
        cryptos.add("MATICUSDT");
        cryptos.add("AXSUSDT");
        cryptos.add("AAVEUSDT");
        cryptos.add("ATOMUSDT");
        cryptos.add("NEOUSDT");
        cryptos.add("DOTUSDT");
        cryptos.add("ETHUSDT");
        cryptos.add("CAKEUSDT");
        cryptos.add("BTCUSDT");
        cryptos.add("BNBUSDT");
        cryptos.add("ADAUSDT");
        cryptos.add("TRXUSDT");
        cryptos.add("AUDIOUSDT");

        DollarPrice dolarHoy = this.dollarPriceNow();

        //TODO: Preguntar si hay alguna forma de obtener una lista de cryptos con la api de binance
        //TODO: Pasar el precio de la crypto como el del dolar a coma.
        //TODO: El date del dolar que se obteien es solo la fecha, no la hora.
        for ( String crypto: cryptos) {
            Cryptoactive activo =  restTemplate.getForObject("https://api.binance.com/api/v3/ticker/price?symbol=" + crypto, Cryptoactive.class);
            activo.setPriceAr(activo.getPrice() * (dolarHoy.getValue()) );
            activo.setQuoteTime(dolarHoy.getDate());
            cryptoassets.add(activo);
        }

        return cryptoassets;
    }


    private DollarPrice dollarPriceNow(){

        /**La unica forma de realizar el llamado a la api del BCRA **/
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "BEARER eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2NjQwMzAwNjEsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJwYWJsbzk3NzU4QGdtYWlsLmNvbSJ9.-mN1KnN1aiznnQwyqKyZCdmMKGOHIgwluJU819JA0nm1gTzYQcNzwluLGvwP6GJKQccdlK_o_EtsnEecmjwxKg");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity entity = new HttpEntity(headers);

        //TODO: Preguntar si hay alguna forma de solo obtener la ultima cotizacion del dolar
        ResponseEntity<List<DollarPrice>> response =
                restTemplate.exchange("https://api.estadisticasbcra.com/usd_of", HttpMethod.GET, entity, new ParameterizedTypeReference<List<DollarPrice>>() {});

        /**El precio actual del dolar**/
        DollarPrice dollarPrice = response.getBody().get(response.getBody().size() - 1);

        return dollarPrice;
    }
}



