package ar.edu.unq.desapp.grupoD022021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto.TransactionDTO;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Activity;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.security.jwt.JwtRequest;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.ActivityService;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController

public class TransactionController {

        @Autowired
        TransactionService transactionService ;
        @RequestMapping("/transaction")
        @CrossOrigin
        public List<Transaction> getAllActivity() {
            return transactionService.getAllTransaction();
        }
        @PostMapping("/addtransaction")
        @CrossOrigin
        public void addTransaccion(@RequestBody TransactionDTO transaction){
                transactionService.addTransaccion(transaction);

        }
        @PostMapping("/confirmtransaction")
        @CrossOrigin
        public void confirmtransaction(@RequestBody TransactionDTO transaction){
                transactionService.transactionConfirmation(transaction);

        }
        @PostMapping("/cancelltransaction")
        @CrossOrigin
        public void cancelltransaction(@RequestBody TransactionDTO transaction, User userCancell){
                transactionService.transactionCancell(transaction,userCancell);

        }

        @GetMapping("/usertransaction")
        @CrossOrigin
        public void getTransactionthatUser(@RequestBody Transaction transaction, User userCancell){
              //  transactionService.getTransactionThatUser(userCancell.getId());

        }
    }
