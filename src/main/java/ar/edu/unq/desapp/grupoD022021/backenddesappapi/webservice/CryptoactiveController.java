package ar.edu.unq.desapp.grupoD022021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Cryptoactive;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.CryptoactiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CryptoactiveController {

    @Autowired
    CryptoactiveService cryptoactiveService ;

    @Cacheable(value = "cryptoassets")
    @GetMapping("/api/cryptoassets")
    @CrossOrigin
    public List<Cryptoactive> getCryptoassets() {
        return cryptoactiveService.getAllCryptoassets();
    }
}



