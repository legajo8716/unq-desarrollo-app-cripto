package ar.edu.unq.desapp.grupoD022021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto.ActivityDto;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.ActivityService;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController

public class ActivityController  {
    @Autowired
    ActivityService activityService ;
    @Autowired
    TransactionService transactionService ;
    @GetMapping("/activities")
    @CrossOrigin
    public List<ActivityDto>  getAllActivity() {
        return activityService.getAllActivity();
    }



    @PostMapping("/addactivity")
    @CrossOrigin
    public ResponseEntity<String> addActivity(@RequestBody ActivityDto activityDto) {
        return new ResponseEntity<>(activityService.addActivity(activityDto), HttpStatus.OK);
    }
    @PostMapping("/activitytotransaction")
    @CrossOrigin
    public void convertActivityToTransaction(@RequestParam int idActivity, String emailUser){
        activityService.convertActivityToTransaction(idActivity, emailUser);
    }
}
