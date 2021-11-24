package ar.edu.unq.desapp.grupoD022021.backenddesappapi.service;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto.ActivityDto;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto.TransactionDTO;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Activity;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Cryptoactive;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories.ActivityRepository;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service

public class ActivityService {
    @Autowired
    ActivityRepository activityRepository;
    @Autowired

    UserRepository userRepository;

    public List<ActivityDto> getAllActivity() {
        List<Activity> activityList = activityRepository.findAll();
        List<ActivityDto> activityListDto = new ArrayList<>();

        for (Activity activity : activityList) {
            User userAux = userRepository.findByEmail(activity.getUsuario().getEmail());
            ActivityDto activityDTOAux = new ActivityDto();
            activityDTOAux.setId(activity.getId());
            activityDTOAux.setCryptoactive(activity.getCryptoactive());
            activityDTOAux.setAction(activity.getAction());
            activityDTOAux.setHour(activity.getHour());
            activityDTOAux.setCantidad(activity.getCantidad());
            activityDTOAux.setFullNameUser(userAux.getName() + " " + userAux.getLastname());
            activityDTOAux.setReputation(userAux.getAwardedPoints());
            activityDTOAux.setNumberOperations(userAux.getNumberOfOperations());
            activityDTOAux.setEmailUser(userAux.getEmail());
            activityListDto.add(activityDTOAux);
        }
        return activityListDto;
    }

    public ResponseEntity<String> addActivity(ActivityDto activityDto) {
        String date = "";

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        date = dtf.format(now);

        if(activityDto.getCryptoactive().equals("BTCUSDT") && activityDto.getCantidad() > 2){
            return new ResponseEntity<>("It is only allowed to buy / sell a maximum of two BTCUSDT", HttpStatus.BAD_REQUEST);
        } else {
            Activity newActivity= new Activity();
            User usuario=userRepository.findByEmail(activityDto.getEmailUser());
            newActivity.setUsuario(usuario);
            newActivity.setCryptoactive(activityDto.getCryptoactive());
            newActivity.setHour(date);
            newActivity.setCantidad(activityDto.getCantidad());
            newActivity.setAction(activityDto.getAction());
            newActivity.setNumberOfOperations(usuario.getNumberOfOperations());
            newActivity.setAwardedPoints(usuario.getAwardedPoints());
            activityRepository.save(newActivity);

            return new ResponseEntity<>("Sale / purchase added successfully", HttpStatus.OK);
        }
    }

    public ActivityDto getActivity(int idActivity) {
        Activity activityWanted=activityRepository.findById(idActivity);
        ActivityDto activityDto= new ActivityDto();
        activityDto.setId(activityWanted.getId());
        activityDto.setEmailUser(activityWanted.getUsuario().getEmail());
        activityDto.setCantidad(activityWanted.getCantidad());
        activityDto.setFullNameUser(activityWanted.getUsuario().getName()+" "+activityWanted.getUsuario().getLastname());
        activityDto.setCryptoactive(activityWanted.getCryptoactive());
        activityDto.setAction(activityWanted.getAction());
        activityDto.setNumberOperations(activityWanted.getUsuario().getNumberOfOperations());
        activityDto.setReputation(activityWanted.getUsuario().getAwardedPoints());


        return activityDto ;
    }

    public void finishActivity(int idActivity) {
        activityRepository.deleteById(idActivity);
    }
}