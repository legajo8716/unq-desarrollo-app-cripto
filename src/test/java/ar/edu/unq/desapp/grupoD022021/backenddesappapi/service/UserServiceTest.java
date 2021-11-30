package ar.edu.unq.desapp.grupoD022021.backenddesappapi.service;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.SpringTestConfig;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto.UserDto;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.util.AssertionErrors.assertTrue;


@RunWith(SpringRunner.class)
    @SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
    @ContextConfiguration(classes = SpringTestConfig.class)
    public class UserServiceTest {


        @Autowired
        UserService userService;
        @MockBean
        UserRepository userRepository;

        @Test
        public void save() {
            User newUser = new User("nelson", "gonzalez", "nel@gmail.com", "12345678", "12345678", "1234567891123456789121", "12345678");
            given(userRepository.save(newUser)).willReturn(newUser);
            userService.save(newUser);
            assertEquals(userService.save(newUser), "Usuario registrado con exito");

        }
        @Test
        public void findAll() {
            User user = new User("nelson", "gonzalez", "nel@gmail.com", "12345678", "12345678", "12345678", "12345678");
            List<User> userList = new ArrayList<>();
            userList.add(user);
            given(userRepository.findAll()).willReturn(userList);
            List<UserDto> userListRetorned = userService.findAll();
            UserDto nelsonDto = new UserDto(user);
            assertEquals(nelsonDto.getName(), userListRetorned.get(0).getName());
            assertEquals(nelsonDto.getLastName(), userListRetorned.get(0).getLastName());

        }
        @Test
        public void existsUser() {
            String email="nel@gmail.com";
            given(userRepository.existsByEmail(email)).willReturn(true);
            assertTrue("OK",userService.existsUser(email));

        }
    }