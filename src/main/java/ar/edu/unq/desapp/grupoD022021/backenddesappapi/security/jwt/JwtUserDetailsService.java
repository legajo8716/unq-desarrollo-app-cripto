package ar.edu.unq.desapp.grupoD022021.backenddesappapi.security.jwt;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.CryptoactiveService;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (userService.existsUser(email)) {
            ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.User persistedUser=userService.findByEmail(email);

            return new User(persistedUser.getEmail(), persistedUser.getPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }
}