package ar.edu.unq.desapp.grupoD022021.backenddesappapi.service;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto.ActivityDto;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto.ResponseDTO;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto.TransactionDTO;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Activity;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories.ActivityRepository;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class ActivityService {
    @Autowired
    ActivityRepository activityRepository;
    @Autowired

    UserRepository userRepository;

    @Autowired
    TransactionService transactionService ;

    public List<ActivityDto> getAllActivity() {
        List<Activity> activityList = activityRepository.findAll();
        List<ActivityDto> activityListDto = new ArrayList<>();

        for (Activity activity : activityList) {
            User userAux = userRepository.findByEmail(activity.getUsuario().getEmail());
            ActivityDto activityDTOAux = new ActivityDto();
            activityDTOAux.setId(activity.getId_activity());
            activityDTOAux.setCryptoactive(activity.getCryptoactive());
            activityDTOAux.setAction(activity.getAction());
            activityDTOAux.setHour(activity.getHour());
            activityDTOAux.setCantidad(activity.getCantidad());
            activityDTOAux.setFullNameUser(userAux.getName() + " " + userAux.getLastname());
            activityDTOAux.setReputation(userAux.getReputation());
            activityDTOAux.setNumberOperations(userAux.getNumberOfOperations());
            activityDTOAux.setEmailUser(userAux.getEmail());
            activityListDto.add(activityDTOAux);
        }
        return activityListDto;
    }

    public ResponseDTO addActivity(ActivityDto activityDto) {
        String date = "";

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        date = dtf.format(now);
        if(!validAmount(activityDto.getCantidad())){
            return new ResponseDTO("Invalid amount", HttpStatus.BAD_REQUEST);
        }
        if(isBTCUSDT(activityDto.getCryptoactive()) && activityDto.getCantidad() > 2){
            return new ResponseDTO("It is only allowed to buy / sell a maximum of two BTCUSDT", HttpStatus.BAD_REQUEST);
        } else {
            Activity newActivity= new Activity();
            User usuario=userRepository.findByEmail(activityDto.getEmailUser());
            newActivity.setUsuario(usuario);
            newActivity.setCryptoactive(activityDto.getCryptoactive());
            newActivity.setHour(date);
            newActivity.setCantidad((activityDto.getCantidad()));
            newActivity.setAction(activityDto.getAction());
            newActivity.setNumberOfOperations(usuario.getNumberOfOperations());
            newActivity.setAwardedPoints(usuario.getAwardedPoints());
            activityRepository.save(newActivity);

            return new ResponseDTO("Sale / purchase added successfully", HttpStatus.OK);
        }
    }

    private Boolean validAmount(Double amount){
        return amount > 0.0;
    }

    private Boolean isBTCUSDT(String crypto){
        return crypto.equals("BTCUSDT");
    }
    public ActivityDto getActivity(int idActivity) {
        Activity activityWanted=activityRepository.findById(idActivity);
        ActivityDto activityDto= new ActivityDto();
        activityDto.setId(activityWanted.getId_activity());
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

    public void convertActivityToTransaction(int idActivity, String emailUser) {

        ActivityDto actividadAux= getActivity(idActivity);
        TransactionDTO transactionDTO=new TransactionDTO();
        transactionDTO.setCryptoactive(actividadAux.getCryptoactive());
        transactionDTO.setEmailUserVendedor(actividadAux.getEmailUser());
        transactionDTO.setEmailUserComprador(emailUser);
        transactionDTO.setCantidad(actividadAux.getCantidad());
        transactionDTO.setAction(actividadAux.getAction());
        transactionDTO.setReputation(actividadAux.getReputation());
        transactionService.addTransaccion(transactionDTO);
        finishActivity(idActivity);

    }
}