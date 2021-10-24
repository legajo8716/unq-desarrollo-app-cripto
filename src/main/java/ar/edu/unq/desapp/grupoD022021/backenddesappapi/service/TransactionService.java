package ar.edu.unq.desapp.grupoD022021.backenddesappapi.service;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Activity;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories.ActivityRepository;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service

public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }
    public void addTransaccion(Transaction transaction){
         Transaction newTransaction= new Transaction();
         DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
         newTransaction.setHour(date);
         newTransaction.setCantidad(transaction.getCantidad());
         newTransaction.setUsuarioVendedor(transaction.getUsuarioVendedor());
         newTransaction.setUsuarioComprador(transaction.getUsuarioComprador());
         newTransaction.setCryptoactive(transaction.getCryptoactive());
         transactionRepository.save(newTransaction);
    }

}
