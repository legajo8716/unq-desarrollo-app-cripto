package ar.edu.unq.desapp.grupoD022021.backenddesappapi.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

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

    public User( String name, String lastname, String email, Integer direction, String password, Integer CVU, Integer wallet) {

        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.direction = direction;
        this.password = password;
        this.CVU = CVU;
        this.wallet = wallet;
    }

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public Integer getDirection() {
        return direction;
    }

    public String getPassword() {
        return password;
    }

    public Integer getCVU() {
        return CVU;
    }

    public Integer getWallet() {
        return wallet;
    }

    public Integer getAwardedPoints() {
        return awardedPoints;
    }

    public Integer getNumberOfOperations() {
        return numberOfOperations;
    }
}
