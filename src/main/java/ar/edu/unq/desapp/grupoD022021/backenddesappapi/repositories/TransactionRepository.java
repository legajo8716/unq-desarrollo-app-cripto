package ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    List<Transaction> findAll();
    Transaction findById(int id);
    boolean deleteById(int id);
    List<Transaction> findByUsuarioCompradorEmail (String email);

    List<Transaction> findByUsuarioVendedorId (Integer idUser);

    List<Transaction>  findByUsuarioVendedorEmail(String email);
}
