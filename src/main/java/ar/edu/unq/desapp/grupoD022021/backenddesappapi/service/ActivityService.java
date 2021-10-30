package ar.edu.unq.desapp.grupoD022021.backenddesappapi.service;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto.ActivityDto;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Activity;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Cryptoactive;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories.ActivityRepository;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories.CryptoactiveRepository;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
@Service

public class ActivityService {
    @Autowired
    ActivityRepository activityRepository;
    @Autowired

    UserRepository userRepository;

    public List<Activity> getAllActivity() {
        return activityRepository.findAll();
    }


    public void addActivity(ActivityDto activityDto) {

        Date date = new Date();
        Activity newActivity= new Activity();
        User usuario=userRepository.findByEmail(activityDto.getEmailUser());
        newActivity.setUsuario(usuario);
        newActivity.setCryptoactive(activityDto.getCryptoactive());
        newActivity.setHour(date);
        newActivity.setCantidad(activityDto.getCantidad());
        newActivity.setAction(activityDto.getAction());
        activityRepository.save(newActivity);
    }

    public ActivityDto getActivity(int idActivity) {
        Activity activityWanted=activityRepository.findById(idActivity);
        ActivityDto activityDto= new ActivityDto();
        activityDto.setId(activityWanted.getId());
        activityDto.setAction(activityWanted.getAction());
        return activityDto ;
    }

    public void finishActivity(int idActivity) {
        activityRepository.deleteById(idActivity);
    }
}