package ar.edu.unq.desapp.grupoD022021.backenddesappapi.service;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto.UserDto;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private List<String> errors = new ArrayList<>();

    @Transactional
    public ResponseEntity<String> save(User model) {
        if(validateUser(model)){
            model.setPassword(passwordEncoder.encode(model.getPassword()));
            model.initializeAwardedPoints();
            model.initializeNumberOfOperations();
            repository.save(model);
            return new ResponseEntity<>("Usuario registrado con exito ", HttpStatus.OK);
        }else {
            return new ResponseEntity<>(this.errors.get(0), HttpStatus.BAD_REQUEST);
        }

    }

    private boolean validateUser(User user){
//TODO: Continuar la validacion del usuario
        this.errors = new ArrayList<>();
        return validCvu(user.getCVU(), 22) && validWallet(user.getWallet(), 8) && isValidEmail(user.getEmail());
    }

    public Boolean isValidEmail(String email){
        String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" +
                "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
        Boolean validEmail = email.matches(emailPattern);
        Boolean emailNoIsEmpty = !email.isEmpty();
        Boolean existEmail = existsUser(email);
        if(!validEmail){
            this.errors.add("Email invalid");
        } else if(existEmail){
            this.errors.add("Email already exists");
        }
        return  emailNoIsEmpty && validEmail && !existEmail;
    }


    private Boolean validCvu(String cvu, int fieldLength){
        Boolean isValidCvu = this.isValidField(cvu, fieldLength);
        Boolean existCvu = existsByCvu(cvu);
        if(!isValidCvu){
            this.errors.add("CVU invalid");
        } else if(existCvu){
            this.errors.add("CVU already exists");
        }
        return isValidCvu && !existCvu ;
    }

    private Boolean validWallet(String wallet, int fieldLength){
        Boolean isValidWallet = this.isValidField(wallet, fieldLength);
        Boolean existWallet = existsByWallet(wallet);
        if(!isValidWallet){
            this.errors.add("Wallet invalid");
        } else if(existWallet){
            this.errors.add("Wallet already exists");
        }
        return isValidWallet && !existWallet ;
    }

    private boolean isValidField(String field, int fieldLength ){
        Boolean validField = (field.matches("[0-9]*") && field.length() == fieldLength);
        return validField;
    }

    public boolean existsByCvu(String cvu){
        return this.repository.existsByCvu(cvu);
    }

    public boolean existsByWallet(String wallet){
        return this.repository.existsByWallet(wallet);
    }

    public List<UserDto> findAll() {
        List<User> users = this.repository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(user -> userDtos.add(new UserDto(user)));
        return userDtos;
    }

    public boolean existsUser(String email) {
        return this.repository.existsByEmail(email);
    }

    public User findByEmail(String email) {
        return this.repository.findByEmail(email);
    }

    public User findById(int idUserVendedor) { return repository.findById(idUserVendedor);}
}
