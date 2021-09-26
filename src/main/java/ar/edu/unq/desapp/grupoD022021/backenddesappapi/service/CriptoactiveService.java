package ar.edu.unq.desapp.grupoD022021.backenddesappapi.service;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Cryptoactive;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories.CryptoactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriptoactiveService {

    @Autowired
    CryptoactiveRepository cryptoactiveRepository;

    public List<Cryptoactive> getAllCryptoassets(){
        List<Cryptoactive> cryptoassets = cryptoactiveRepository.findAll();
        return cryptoassets;
    }


}
