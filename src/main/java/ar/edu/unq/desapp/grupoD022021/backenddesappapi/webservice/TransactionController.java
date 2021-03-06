package ar.edu.unq.desapp.grupoD022021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto.TransactionDTO;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController

public class TransactionController {

        @Autowired
        TransactionService transactionService ;
        @GetMapping("/transaction")
        @CrossOrigin
        public List<TransactionDTO> getAllTransaction() {
            return transactionService.getAllTransaction();
        }
        @PostMapping("/addtransaction")
        @CrossOrigin
        public void addTransaccion(@RequestBody TransactionDTO transaction){
                transactionService.addTransaccion(transaction);

        }
        @PostMapping("/confirmtransaction")
        @CrossOrigin
        public void confirmtransaction(@RequestParam  int idTransaction){
                transactionService.transactionConfirmation(idTransaction);

        }

        @PostMapping("/cancelltransaction")
        @CrossOrigin
        public void cancelltransaction(@RequestParam int idTransaction){
                transactionService.transactionCancell(idTransaction);
        }

        @GetMapping("/usertransaction")
        @CrossOrigin
        public List<TransactionDTO> getTransactionthatUser(@RequestParam  String email){
              return  transactionService.getTransactionThatUser(email);

        }
    }
