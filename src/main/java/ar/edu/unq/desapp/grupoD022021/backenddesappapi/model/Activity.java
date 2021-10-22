package ar.edu.unq.desapp.grupoD022021.backenddesappapi.model;

import javax.persistence.*;

@Entity
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    int hour;
    Cryptoactive cryptoactive;
    int cantidad;
    User usuario;

}
