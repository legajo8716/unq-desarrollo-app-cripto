package ar.edu.unq.desapp.grupoD022021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.UserService;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.utility.CreateDatosFake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CreateDatosFake createDatosFake;
    @GetMapping("/api/users")
    public List<User> allUser(){
        List<User> users = userService.findAll();
        return users;
    }
    @GetMapping("/init")
    public void init(){
       createDatosFake.generateUser();
    }

}
