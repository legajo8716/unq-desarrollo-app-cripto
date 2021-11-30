package ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.User;
import java.io.Serializable;

public class UserDto  implements Serializable {

    private String name;

    private String lastName;

    private Integer numberOfOperations;

    private Integer awardedPoints;
    private Double reputation;


    public Double getReputation() {
        return reputation;
    }

    public void setReputation(Double reputation) {
        this.reputation = reputation;
    }


    public UserDto(User user) {
        this.name = user.getName();
        this.lastName = user.getLastname();
        this.numberOfOperations = user.getNumberOfOperations();
        this.awardedPoints = user.getAwardedPoints();
        this.reputation=user.getReputation();
    }

    //TODO: Sin los getters and setters no funciona D:
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getNumberOfOperations() {
        return numberOfOperations;
    }

    public void setNumberOfOperations(Integer numberOfOperations) {
        this.numberOfOperations = numberOfOperations;
    }

    public Integer getAwardedPoints() {
        return awardedPoints;
    }

    public void setAwardedPoints(Integer awardedPoints) {
        this.awardedPoints = awardedPoints;
    }
}
