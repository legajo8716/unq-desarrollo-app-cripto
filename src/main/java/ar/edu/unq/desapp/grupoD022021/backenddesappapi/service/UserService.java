package ar.edu.unq.desapp.grupoD022021.backenddesappapi.service;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional
    public User save(User model){
        return repository.save(model);
    }

    public List<User> findAll() {
        return this.repository.findAll();
    }

    public boolean existsUser(String email) {
        return this.repository.existsByEmail(email);
    }

    public User findByEmail(String email) {
        return this.repository.findByEmail(email);
    }

    public User findById(int idUserVendedor) { return repository.findById(idUserVendedor);}
}
