package ar.edu.unq.desapp.grupoD022021.backenddesappapi.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PointHandler {

    @Value("${cantTimeOnSecondExpired}")
    private int cantTimeOnSecondExpired;
    @Value("${cantPointNotExpired}")
    private int cantPointNotExpired;
    @Value("${cantPointExpired}")
    private int cantPointExpired;
    @Value("${cantPointCancel}")
    private int cantPointCancel;



    public int getPointConfirmTransaction(Transaction transaction){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime created=transaction.getHour();
        if(this.beatsTimeExpired(now,created)){
            return this.cantPointExpired;}
        else {
            return this.cantPointNotExpired;
        }
    }
    private boolean beatsTimeExpired(LocalDateTime now, LocalDateTime created) {
        return now.getYear()==created.getYear()&&
                now.getMonth()==created.getMonth()&&
                now.getDayOfWeek()==created.getDayOfWeek()&&
                ( ( ((now.getHour()*60*60)+(now.getMinute()*60)+now.getSecond())-((created.getHour()*60*60)+(created.getMinute()*60)+created.getSecond()))>this.cantTimeOnSecondExpired);
    }

    public int getPointCancelTransaction(){
        return cantPointCancel;
    }
    public Double getReputacion(User user) {
        if (user.getNumberOfOperations() >0.0 && user.getAwardedPoints()>0)
            return  user.getAwardedPoints() / (double) user.getNumberOfOperations();
        else
            return 0.0;
    }


}
