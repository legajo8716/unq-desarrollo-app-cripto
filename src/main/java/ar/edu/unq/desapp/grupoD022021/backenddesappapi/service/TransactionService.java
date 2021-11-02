package ar.edu.unq.desapp.grupoD022021.backenddesappapi.service;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto.TransactionDTO;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.PointHandler;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<TransactionDTO> getAllTransaction() {
        List<Transaction> transactionList=transactionRepository.findAll();

        return this.convertDtoList(transactionList);
    }
    public void addTransaccion(TransactionDTO transaction){
         Transaction newTransaction= new Transaction();
         Date date = new Date();newTransaction.setHour(date);
         newTransaction.setCantidad(transaction.getCantidad());
         newTransaction.setUsuarioVendedor(userService.findByEmail(transaction.getEmailUserVendedor()));
        newTransaction.setUsuarioComprador(userService.findByEmail(transaction.getEmailUserComprador()));

        newTransaction.setCryptoactive(transaction.getCryptoactive());
         transactionRepository.save(newTransaction);
    }
    public void transactionConfirmation(int idTransaction){
        Transaction transactionUpdate=transactionRepository.findById(idTransaction);
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
   public List<TransactionDTO> getTransactionThatUser(String email) {
          return this.convertDtoList(transactionRepository.findByUsuarioVendedorEmail(email)) ;
    }
    public List<TransactionDTO> convertDtoList(List<Transaction> transactionList){
        List<TransactionDTO> transactionDTOList=new ArrayList<TransactionDTO>();
        for (Transaction transaction : transactionList){
            TransactionDTO transactionDTOAux= new TransactionDTO();
            transactionDTOAux.setCantidad(transaction.getCantidad());
            transactionDTOAux.setId(transaction.getId());
            transactionDTOAux.setCryptoactive(transaction.getCryptoactive());
            transactionDTOAux.setHour(transaction.getHour());
            transactionDTOAux.setFinalished(transaction.getFinalished());
            transactionDTOAux.setEmailUserVendedor(transaction.getUsuarioVendedor().getEmail());
            transactionDTOAux.setUsuarioVendedor(transaction.getUsuarioVendedor().getName() + " "+ transaction.getUsuarioVendedor().getLastname());
            if(transaction.getUsuarioComprador()!=null) {
                transactionDTOAux.setEmailUserComprador(transaction.getUsuarioComprador().getEmail());
                transactionDTOAux.setUsuarioComprador(transaction.getUsuarioComprador().getName() + " " + transaction.getUsuarioComprador().getLastname());
            }
            transactionDTOList.add(transactionDTOAux);
        }
        return transactionDTOList;
    }



}
