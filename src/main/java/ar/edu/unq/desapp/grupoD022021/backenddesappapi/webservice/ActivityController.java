package ar.edu.unq.desapp.grupoD022021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Activity;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Cryptoactive;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.ActivityService;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.CryptoactiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController

public class ActivityController  {
    @Autowired
    ActivityService activityService ;
    public List<Activity> getAllActivity() {
        return activityService.getAllActivity();
    }
}
