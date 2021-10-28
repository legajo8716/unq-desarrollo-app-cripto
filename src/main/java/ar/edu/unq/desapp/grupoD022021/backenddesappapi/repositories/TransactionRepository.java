package ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Cryptoactive;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    List<Transaction> findAll();
    List<Transaction> findByIdAndIdUsuarioComprador(int id);
    List<Transaction> findByIdAndIdUsuarioVendedores(int id);

}
