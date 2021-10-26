package ar.edu.unq.desapp.grupoD022021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Activity;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.security.jwt.JwtRequest;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.ActivityService;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        @RequestMapping("/addtransaction")
        @CrossOrigin
        public void addTransaccion(@RequestBody Transaction transaction){
                transactionService.addTransaccion(transaction);

        }
    }
