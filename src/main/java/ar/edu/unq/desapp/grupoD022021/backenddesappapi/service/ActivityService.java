package ar.edu.unq.desapp.grupoD022021.backenddesappapi.service;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Activity;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Cryptoactive;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories.ActivityRepository;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories.CryptoactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service

public class ActivityService {
    @Autowired
    ActivityRepository activityRepository;
    public List<Activity> getAllActivity() {
        return activityRepository.findAll();
    }


}