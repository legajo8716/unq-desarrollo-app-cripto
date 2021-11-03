package ar.edu.unq.desapp.grupoD022021.backenddesappapi.model;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PointHandler {
// Todo:Ver donde se puede setear estos valores para no tener que dejarlos harcodeados
    private int cantTimeOnSecondExpired =30*60;
    private int cantPointNotExpired=10;
    private int cantPointExpired=5;
    private int cantPointCancel=20;

   // public PointHandler(int cantTimeOnSecond, int cantPointNotExpired, int cantPointExpired, int cantPointCancel) {
     //   this.cantTimeOnSecondExpired = cantTimeOnSecond;
       // this.cantPointNotExpired = cantPointNotExpired;
        //this.cantPointExpired = cantPointExpired;
       // this.cantPointCancel = cantPointCancel;
    //}

    public int getPointConfirmTransaction(Transaction transaction){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime created=LocalDateTime.now();
        if(this.beatsTimeExpired(now,created))
            return this.cantPointExpired;
        else
            return this.cantPointNotExpired;

    }
    private boolean beatsTimeExpired(LocalDateTime now, LocalDateTime created) {
        return now.getYear()==created.getYear()&&
                now.getMonth()==created.getMonth()&&
                now.getDayOfWeek()==created.getDayOfWeek()&&
                now.getHour()*now.getMinute()*now.getSecond()-created.getHour()*created.getMinute()*created.getSecond()<=this.cantTimeOnSecondExpired;
    }

    public int getPointCancelTransaction(){
        return cantPointCancel;
    }
    public int getReputacion(User user) {
        if (user.getNumberOfOperations() >0)
            return user.getAwardedPoints() / user.getNumberOfOperations();
        else
            return 0;
    }


}
