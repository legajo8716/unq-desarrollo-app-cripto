package ar.edu.unq.desapp.grupoD022021.backenddesappapi.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", length = 30, nullable = false)
    private String name;
    @Column(name = "lastname", length = 30, nullable = false)
    private String lastname;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "direction", nullable = false, length = 30)
    private String direction;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "cvu", nullable = false, length = 22, unique = true)
    private String cvu;
    @Column(name = "wallet", nullable = false, length = 8, unique = true)
    private String wallet;
    @Column(name = "awarded_points")
    private Integer awardedPoints;
    @Column(name = "number_of_operations")
    private Integer numberOfOperations;
    @Column(name = "point_negative")
    private Double pointNegative;

    @Column(name = "reputation")
    private Double reputation;
    public User( String name, String lastname, String email, String direction, String password, String cvu, String wallet) {

        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.direction = direction;
        this.password = password;
        this.cvu = cvu;
        this.wallet = wallet;
        this.numberOfOperations=0;
        this.pointNegative=0.0;
        this.awardedPoints=0;
        this.reputation=0.0;
    }

    public User() {
        this.numberOfOperations=0;
        this.pointNegative=0.0;
        this.awardedPoints=0;
        this.reputation=0.0;
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

    public String getDirection() {
        return direction;
    }

    public String getPassword() {
        return password;
    }

    public String getCVU() {
        return cvu;
    }

    public String getWallet() {
        return wallet;
    }

    public Integer getAwardedPoints() {
        return awardedPoints;
    }

    public Integer getNumberOfOperations() {
        return numberOfOperations;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAwardedPoints(Integer awardedPoints) {
        this.awardedPoints += awardedPoints;
    }

    public void setAwardedPointsOfOperationCancelled(int awardedPoints){
        if(this.awardedPoints - awardedPoints > 0){
            this.awardedPoints -= awardedPoints;
        } else {
            this.awardedPoints = 0;
        }
    }
    public Double getReputation() {
        return reputation;
    }

    public void setReputation(Double reputation) {
        this.reputation = reputation;
    }
    public void initializeNumberOfOperations(){
        this.numberOfOperations = 0;
    }
    public void initializeAwardedPoints(){
        this.awardedPoints = 0;
    }
    public void sumTransactionConfirmed() {
        this.numberOfOperations++;

    }

    public void sumAwardedPoints(int point) {
        this.awardedPoints=this.awardedPoints+point;
    }
}
