package ar.edu.unq.desapp.grupoD022021.backenddesappapi.service;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto.TransactionDTO;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.PointHandler;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service

public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserService userService;

    @Autowired
    PointHandler pointHandler;
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }
    public void addTransaccion(TransactionDTO transaction){
         Transaction newTransaction= new Transaction();
         Date date = new Date();newTransaction.setHour(date);
         newTransaction.setCantidad(transaction.getCantidad());
         newTransaction.setUsuarioVendedor(userService.findById(transaction.getIdUserVendedor()));
         newTransaction.setUsuarioComprador(userService.findById(transaction.getIdUserComprador()));
         newTransaction.setCryptoactive(transaction.getCryptoactive());
         transactionRepository.save(newTransaction);
    }
    public void transactionConfirmation(TransactionDTO transaction){
        Transaction transactionUpdate=transactionRepository.findById(transaction.getId());
        User usuarioCompradorUpdate=transactionUpdate.getUsuarioComprador();
        User usuarioVendedorUpdate=transactionUpdate.getUsuarioVendedor();
        usuarioVendedorUpdate.sumAwardedPoints(pointHandler.getPointConfirmTransaction(transactionUpdate));
        usuarioCompradorUpdate.sumAwardedPoints(pointHandler.getPointConfirmTransaction(transactionUpdate));
        transactionUpdate.confirm();
        userService.save(usuarioCompradorUpdate);
        userService.save(usuarioVendedorUpdate);
        transactionRepository.save(transactionUpdate);
    }

    public void transactionCancell(TransactionDTO transaction, User userTransactionCancelled){
        User userUpdate=userTransactionCancelled;
        userUpdate.setReputation(pointHandler.getReputacion(userUpdate)-20);
        transactionRepository.deleteById(transaction.getId());


    }
   public List<Transaction> getTransactionThatUser(Integer id) {
        List<Transaction> transactionsResult = Stream.concat(transactionRepository.findByUsuarioCompradorId(id).stream(),
                                                  transactionRepository.findByUsuarioCompradorId(id).stream())
                .collect(Collectors.toList());
        return transactionsResult ;
    }
}
