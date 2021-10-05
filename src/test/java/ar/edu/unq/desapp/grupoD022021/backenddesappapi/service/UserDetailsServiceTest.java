package ar.edu.unq.desapp.grupoD022021.backenddesappapi.service;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.SpringTestConfig;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Cryptoactive;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.DollarPrice;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;





    @RunWith(SpringRunner.class)
    @SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
    @ContextConfiguration(classes = SpringTestConfig.class)
    public class UserDetailsServiceTest {


        @MockBean
        UserService userService;
        @Autowired
        UserDetailsService userDetailsService;

        @Test
        public void loadUserByUsernameOk() throws Exception {
            String email="nel@gmail.com";
            User usuarioPersistido=new User();
            usuarioPersistido.setEmail(email);
            usuarioPersistido.setPassword("12345678");
            given(userService.existsUser(email)).willReturn(true);
            given(userService.findByEmail(email)).willReturn(usuarioPersistido);
            UserDetails userJwt=userDetailsService.loadUserByUsername(email);
            assertTrue(userJwt.getClass()==org.springframework.security.core.userdetails.User.class);
        }

        @Test(expected= UsernameNotFoundException.class)
        public void loadUserByUsernameNoOk() throws Exception {
            String email="nel@gmail.com";
            given(userService.existsUser(email)).willReturn(false);
            UserDetails userJwt=userDetailsService.loadUserByUsername(email);
        }

    }
