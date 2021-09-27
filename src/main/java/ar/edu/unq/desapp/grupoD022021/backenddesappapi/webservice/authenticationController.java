package ar.edu.unq.desapp.grupoD022021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class authenticationController {

    @GetMapping("/login")
    public boolean login(@RequestBody User user){

        return true;
    }
    @PostMapping("/register")
    public boolean register(@RequestBody User user){

        return true;
    }


}
