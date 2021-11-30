package ar.edu.unq.desapp.grupoD022021.backenddesappapi.service;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.SpringTestConfig;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto.ActivityDto;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto.UserDto;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Activity;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories.ActivityRepository;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = SpringTestConfig.class)
public class ActivityServiceTest {

    @Autowired
    ActivityService activityService;

    @MockBean
    UserRepository userRepository;

    @MockBean
    ActivityRepository activityRepository;

    @Test
    public void findAll() {
        User user = new User("nelson", "gonzalez", "nel@gmail.com", "12345678", "12345678", "12345678", "12345678");
        Activity activity1 = new Activity("30/11/2021 11:45", "BTCUSDT", 1.0, user, "sell", 2.0, 3);
        List<Activity> activityList = new ArrayList<>();
        activityList.add(activity1);

        given(userRepository.findByEmail("nel@gmail.com")).willReturn(user);
        given(activityRepository.findAll()).willReturn(activityList);

        List<ActivityDto> activityDtoList = activityService.getAllActivity();
        ActivityDto activityDto = new ActivityDto(activity1);

        activityDto.setId(activity1.getId_activity());
        activityDto.setAction(activity1.getAction());
        activityDto.setFullNameUser(user.getName() + " " + user.getLastname());
        activityDto.setReputation(user.getReputation());
        activityDto.setNumberOperations(user.getNumberOfOperations());
        activityDto.setEmailUser(user.getEmail());
        assertEquals(activityDtoList.get(0).getHour(), activity1.getHour());
    }

    @Test
    public void addActivitySuccess() {
        User user = new User("nelson", "gonzalez", "nel@gmail.com", "12345678", "12345678", "12345678", "12345678");
        Activity activity1 = new Activity("30/11/2021 11:45", "BTCUSDT", 1.0, user, "sell", 2.0, 3);
        List<Activity> activityList = new ArrayList<>();
        activityList.add(activity1);
        ActivityDto activityDto = new ActivityDto(activity1);

        activityDto.setId(activity1.getId_activity());
        activityDto.setAction(activity1.getAction());
        activityDto.setFullNameUser(user.getName() + " " + user.getLastname());
        activityDto.setReputation(user.getReputation());
        activityDto.setNumberOperations(user.getNumberOfOperations());
        activityDto.setEmailUser(user.getEmail());
        given(userRepository.findByEmail("nel@gmail.com")).willReturn(user);

        String result = activityService.addActivity(activityDto);
        assertEquals(result, "Sale / purchase added successfully");
    }

    @Test
    public void addActivityError() {
        User user = new User("nelson", "gonzalez", "nel@gmail.com", "12345678", "12345678", "12345678", "12345678");
        Activity activity1 = new Activity("30/11/2021 11:45", "BTCUSDT", 2.1, user, "sell", 2.0, 3);
        List<Activity> activityList = new ArrayList<>();
        activityList.add(activity1);
        ActivityDto activityDto = new ActivityDto(activity1);

        activityDto.setId(activity1.getId_activity());
        activityDto.setAction(activity1.getAction());
        activityDto.setFullNameUser(user.getName() + " " + user.getLastname());
        activityDto.setReputation(user.getReputation());
        activityDto.setNumberOperations(user.getNumberOfOperations());
        activityDto.setEmailUser(user.getEmail());
        given(userRepository.findByEmail("nel@gmail.com")).willReturn(user);

        String result = activityService.addActivity(activityDto);
        assertEquals(result, "It is only allowed to buy / sell a maximum of two BTCUSDT");
    }

    @Test
    public void getActivity(){
        User user = new User("nelson", "gonzalez", "nel@gmail.com", "12345678", "12345678", "12345678", "12345678");
        Activity activity1 = new Activity("30/11/2021 11:45", "BTCUSDT", 1.0, user, "sell", 2.0, 3);
        given(activityRepository.findById(1)).willReturn(activity1);
        ActivityDto activityDto = activityService.getActivity(1);
        assertEquals(activityDto.getCantidad(), 1.0);
        assertEquals(activityDto.getCryptoactive(), "BTCUSDT");

    }
}
