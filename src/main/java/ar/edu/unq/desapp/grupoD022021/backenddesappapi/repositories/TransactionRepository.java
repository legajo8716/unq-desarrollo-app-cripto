package ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Cryptoactive;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Transaction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;


public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    List<Transaction> findAll();
    Transaction findById(int id);
    boolean deleteById(int id);
    List<Transaction> findByUsuarioCompradorId (Integer idUser);

    List<Transaction> findByUsuarioVendedorId (Integer idUser);
}
