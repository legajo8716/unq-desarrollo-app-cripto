package ar.edu.unq.desapp.grupoD022021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class authenticationController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public boolean login() {

        return true;
    }

    @PostMapping("/register")
    public boolean register(User user) {
        User newUser = new User(user.getName(),
                                user.getLastname(),
                                user.getEmail(),
                                user.getDirection(),
                                passwordEncoder.encode(user.getPassword()),
                                user.getCVU(), user.getWallet());
        userService.save(user);
        return true;
    }


}
