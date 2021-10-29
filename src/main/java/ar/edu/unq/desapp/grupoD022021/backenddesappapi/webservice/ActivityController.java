package ar.edu.unq.desapp.grupoD022021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto.ActivityDto;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto.TransactionDTO;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Activity;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.ActivityService;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController

public class ActivityController  {
    @Autowired
    ActivityService activityService ;
    @Autowired
    TransactionService transactionService ;
    @RequestMapping("/activities")
    @CrossOrigin
    public List<Activity> getAllActivity() {
        return activityService.getAllActivity();
    }


    @RequestMapping("/addactivity")
    @CrossOrigin
    public void addActivity(ActivityDto activityDto) { activityService.addActivity(activityDto); }
    @RequestMapping("/activitytotransaction")
    @CrossOrigin
    public void convertActivityToTransaction(@RequestParam int idActivity, @RequestParam int idUser){
        Activity actividadAux=activityService.getActivity(idActivity);
        TransactionDTO transactionDTO=new TransactionDTO();
        transactionDTO.setIdUserVendedor(idUser);
        transactionDTO.setCryptoactive(actividadAux.getCryptoactive());
        transactionDTO.setCantidad(actividadAux.getCantidad());
        transactionService.addTransaccion(transactionDTO);
        activityService.finishActivity(idActivity);

    }
}
