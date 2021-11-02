package ar.edu.unq.desapp.grupoD022021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto.ActivityDto;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto.TransactionDTO;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.ActivityService;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController

public class ActivityController  {
    @Autowired
    ActivityService activityService ;
    @Autowired
    TransactionService transactionService ;
    @RequestMapping("/activities")
    @CrossOrigin
    public List<ActivityDto>  getAllActivity() {
        return activityService.getAllActivity();
    }



    @PostMapping("/addactivity")
    @CrossOrigin
    public void addActivity(@RequestBody ActivityDto activityDto) {
        activityService.addActivity(activityDto); }
    @RequestMapping("/activitytotransaction")
    @CrossOrigin
    public void convertActivityToTransaction(@RequestParam int idActivity, String emailUser){
        ActivityDto actividadAux=activityService.getActivity(idActivity);
        TransactionDTO transactionDTO=new TransactionDTO();
        transactionDTO.setCryptoactive(actividadAux.getCryptoactive());
        transactionDTO.setEmailUserVendedor(actividadAux.getEmailUser());
        transactionDTO.setEmailUserComprador(emailUser);
        transactionDTO.setCantidad(actividadAux.getCantidad());
        transactionService.addTransaccion(transactionDTO);
        activityService.finishActivity(idActivity);

    }
}
