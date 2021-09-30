package ar.edu.unq.desapp.grupoD022021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Cryptoactive;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.CryptoactiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class CryptoactiveController {

    @Autowired
    CryptoactiveService cryptoactiveService ;

    //TODO: Hacer de la lista de cryptos enums
    @RequestMapping("/api/cryptoassets")
    public List<Cryptoactive> getCryptoassets() {
        return cryptoactiveService.getAllCryptoassets();
    }
}



