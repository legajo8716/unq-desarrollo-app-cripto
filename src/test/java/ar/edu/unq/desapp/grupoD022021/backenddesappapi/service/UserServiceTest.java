package ar.edu.unq.desapp.grupoD022021.backenddesappapi.service;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.SpringTestConfig;
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
import static org.springframework.test.util.AssertionErrors.assertTrue;


@RunWith(SpringRunner.class)
    @SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
    @ContextConfiguration(classes = SpringTestConfig.class)
    public class UserServiceTest {


        @Autowired
        UserService userService;
        @MockBean
        UserRepository userRepository;

        /*   @Transactional
           public User save(User model){
               return repository.save(model);
           }

           public List<User> findAll() {
               return this.repository.findAll();
           }

           public boolean existsUser(String email) {
               return this.repository.existsByEmail(email);
           }

           public User findByEmail(String email) {
               return this.repository.findByEmail(email);
           }*/
        @Test
        public void save() {
            User newUser = new User("nelson", "gonzalez", "nel@gmail.com", 12345678, "12345678", 12345678, 12345678);
            given(userRepository.save(newUser)).willReturn(newUser);
            userService.save(newUser);
            assertEquals(userService.save(newUser), newUser);

        }
        @Test
        public void findAll() {
            User user = new User("nelson", "gonzalez", "nel@gmail.com", 12345678, "12345678", 12345678, 12345678);
            ArrayList<User> userList=new ArrayList<>();
            userList.add(user);
            given(userRepository.findAll()).willReturn(userList);
            ArrayList<User> userListRetorned= (ArrayList<User>) userService.findAll();
            assertEquals(userList, userListRetorned);

        }
        @Test
        public void existsUser() {
            String email="nel@gmail.com";
            given(userRepository.existsByEmail(email)).willReturn(true);
            assertTrue("OK",userService.existsUser(email));

        }
    }