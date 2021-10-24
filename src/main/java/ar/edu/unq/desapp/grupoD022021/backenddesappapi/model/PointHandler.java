package ar.edu.unq.desapp.grupoD022021.backenddesappapi.model;

import java.time.LocalDateTime;

public class PointHandler {
// Todo:Ver donde se puede setear estos valores para no tener que dejarlos harcodeados
    int cantTimeOnSecondExpired =30*60;
    int cantPointNotExpired=10;
    int cantPointExpired=5;
    int cantPointCancel=0;

    public PointHandler(int cantTimeOnSecond, int cantPointNotExpired, int cantPointExpired, int cantPointCancel) {
        this.cantTimeOnSecondExpired = cantTimeOnSecond;
        this.cantPointNotExpired = cantPointNotExpired;
        this.cantPointExpired = cantPointExpired;
        this.cantPointCancel = cantPointCancel;
    }

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

    public int getPointCancelTransaction(Transaction transaction){
        return cantPointCancel;
    }



}
