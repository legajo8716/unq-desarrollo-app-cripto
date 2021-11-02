package ar.edu.unq.desapp.grupoD022021.backenddesappapi.utility;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto.TransactionDTO;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.TransactionService;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class CreateDatosFake {

    public CreateDatosFake() {
    }
    @Autowired
    UserService userService;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    TransactionService transactionService;
    public void generateUser(){
        User newUser=new User("Nelson","Gonzalez","nel@gmail.com",123,"1234567","1234567","1234567");
        User newUser2=new User("Nestor","Gonzalez","nes@gmail.com",123,"1234567","1234567","1234567");

        userService.save(newUser);
    }




}
