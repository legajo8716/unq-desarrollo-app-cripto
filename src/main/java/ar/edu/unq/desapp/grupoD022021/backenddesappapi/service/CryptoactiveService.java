package ar.edu.unq.desapp.grupoD022021.backenddesappapi.service;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Cryptoactive;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.DollarPrice;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories.CryptoactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CryptoactiveService {

    @Autowired
    CryptoactiveRepository cryptoactiveRepository;


    @Autowired
    RestTemplate restTemplate;


    public List<Cryptoactive> getAllCryptoassets(){
        List<Cryptoactive> cryptoassets = new ArrayList<>();
        List<String> cryptos = new ArrayList<>();
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

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<List<Cryptoactive>> response =
                restTemplate.exchange("https://api.binance.com/api/v3/ticker/price?", HttpMethod.GET, entity, new ParameterizedTypeReference<List<Cryptoactive>>() {});

        List<Cryptoactive > result = response.getBody();

        Integer count = 0;
        Integer next = result.size() - 1 ;
        while(count < cryptos.size()){
            Cryptoactive current = result.get( next );
            if(cryptos.contains(current.getSymbol())){
                current.setPriceAr(current.getPrice() * (dolarHoy.getValue()) );
                current.setQuoteTime(dolarHoy.getDate());
                count++;
                cryptoassets.add(current);
            }
            next--;
        }
        return cryptoassets;
    }


    private DollarPrice dollarPriceNow(){

        /**La unica forma de realizar el llamado a la api del BCRA **/
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "BEARER eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2NjQwMzAwNjEsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJwYWJsbzk3NzU4QGdtYWlsLmNvbSJ9.-mN1KnN1aiznnQwyqKyZCdmMKGOHIgwluJU819JA0nm1gTzYQcNzwluLGvwP6GJKQccdlK_o_EtsnEecmjwxKg");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<List<DollarPrice>> response =
                restTemplate.exchange("https://api.estadisticasbcra.com/usd_of", HttpMethod.GET, entity, new ParameterizedTypeReference<List<DollarPrice>>() {});

        /**El precio actual del dolar**/
        DollarPrice dollarPrice = response.getBody().get(response.getBody().size() - 1);

        return dollarPrice;
    }

}
