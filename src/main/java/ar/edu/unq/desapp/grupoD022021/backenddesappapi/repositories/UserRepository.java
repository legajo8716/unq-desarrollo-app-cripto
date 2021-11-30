package ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {


    List<User> findAll();
    Boolean existsByEmail(String email);
    Boolean existsByCvu(String cvu);
    Boolean existsByWallet(String wallet);
    User findByEmail(String email);
    User findById(int id );
}
