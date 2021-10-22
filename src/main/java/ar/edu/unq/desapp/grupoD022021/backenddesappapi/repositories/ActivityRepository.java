package ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Activity;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Cryptoactive;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActivityRepository extends CrudRepository<Activity, Integer> {
    List<Activity> findAll();

}