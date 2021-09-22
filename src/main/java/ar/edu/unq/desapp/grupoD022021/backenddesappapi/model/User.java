package ar.edu.unq.desapp.grupoD022021.backenddesappapi.model;

import javax.persistence.*;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name", length = 30, nullable = false)
    private String name;
    @Column(name = "lastname", length = 30, nullable = false)
    private String lastname;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "direction", nullable = false, length = 8)
    private Integer direction;
    @Column(name = "password", nullable = false, length = 8)
    private String password;
    @Column(name = "CVU", nullable = false, length = 22)
    private Integer CVU;
    @Column(name = "wallet", nullable = false, length = 8)
    private Integer wallet;

    @Column(name = "awarded_points")
    private Integer awardedPoints;
    @Column(name = "number_of_operations")
    private Integer numberOfOperations;

    public Person(Integer id, String name, String lastname, String email, Integer direction, String password, Integer CVU, Integer wallet) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.direction = direction;
        this.password = password;
        this.CVU = CVU;
        this.wallet = wallet;
    }
}
