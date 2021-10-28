package ar.edu.unq.desapp.grupoD022021.backenddesappapi.service;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto.TransactionDTO;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.PointHandler;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories.TransactionRepository;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service

public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PointHandler pointHandler;
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }
    public void addTransaccion(Transaction transaction){
         Transaction newTransaction= new Transaction();
         Date date = new Date();newTransaction.setHour(date);
         newTransaction.setCantidad(transaction.getCantidad());
         newTransaction.setUsuarioVendedor(transaction.getUsuarioVendedor());
         newTransaction.setUsuarioComprador(transaction.getUsuarioComprador());
         newTransaction.setCryptoactive(transaction.getCryptoactive());
         transactionRepository.save(newTransaction);
    }
    public void transactionConfirmation(Transaction transaction){
        Transaction transactionUpdate=transaction;
        User usuarioCompradorUpdate=transaction.getUsuarioComprador();
        User usuarioVendedorUpdate=transaction.getUsuarioVendedor();
        usuarioVendedorUpdate.sumAwardedPoints(pointHandler.getPointConfirmTransaction(transaction));
        usuarioCompradorUpdate.sumAwardedPoints(pointHandler.getPointConfirmTransaction(transaction));
        transactionUpdate.confirm();
        userRepository.save(usuarioCompradorUpdate);
        userRepository.save(usuarioVendedorUpdate);
        transactionRepository.save(transactionUpdate);

    }

    public void transactionCancell(Transaction transaction,User userTransactionCancelled){
        User userUpdate=userTransactionCancelled;
        userUpdate.setReputation(pointHandler.getReputacion(userUpdate)-20);
        transactionRepository.delete(transaction);


    }
    public List<Transaction> getTransactionThatUser(Integer id) {
        List<Transaction> transactionsResult = Stream.concat(transactionRepository.findByIdAndIdUsuarioComprador(id).stream(),
                                                  transactionRepository.findByIdAndIdUsuarioComprador(id).stream())
                .collect(Collectors.toList());
        return transactionsResult ;
    }
}
