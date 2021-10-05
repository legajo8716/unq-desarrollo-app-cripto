package ar.edu.unq.desapp.grupoD022021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Cryptoactive;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.security.jwt.JwtRequest;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.security.jwt.JwtTokenUtil;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.security.jwt.JwtUserDetailsService;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.CryptoactiveService;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

    @RunWith(SpringRunner.class)
    @SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
    @AutoConfigureMockMvc
    public class JwtAuthenticationControllerTest {

        @Autowired
        private MockMvc mvc;


        @MockBean
        private JwtUserDetailsService userDetailsService;


        @MockBean
        private AuthenticationManager authenticationManager;

        @MockBean
        private JwtTokenUtil jwtTokenUtil;


        @MockBean
        private PasswordEncoder passwordEncoder;

        @MockBean
        private UserService userService;

        @Before
        public void setUp() throws Exception {
        }
        private  JwtRequest authenticationRequest;




        @Test
        public void autehnticationOkTest() throws Exception {
            authenticationRequest=new JwtRequest();
            authenticationRequest.setUsername("nel@gmail.cm");
            authenticationRequest.setPassword("12345678");
            User userSpring=new User("nel@gmail.com", "12345678",
                    new ArrayList<>());
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            String requestJson=ow.writeValueAsString(authenticationRequest );
            given(userDetailsService.loadUserByUsername(authenticationRequest.getUsername())).willReturn(userSpring);
            given(jwtTokenUtil.generateToken(userSpring)).willReturn("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuZWxAZ21haWwuY29tIiwiZXhwIjoxNjMzMzAzOTYxLCJpYXQiOjE2MzMyODU5NjF9.ERdjG6KoM5Z6VIEy7I48ZaHToJ7-dyrEutDl2hUQfjnpKZLDxqLFGQ3vf30Lb1gv-3fNz0bXYtLmTdYs7L5q6A");
            mvc.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(status().isOk());
            verify(userDetailsService, VerificationModeFactory.times(1)).loadUserByUsername(authenticationRequest.getUsername());
            reset(userDetailsService);
            verify(jwtTokenUtil, VerificationModeFactory.times(1)).generateToken(userSpring);
            reset(userDetailsService);
        }



        @Test
        public void autehnticationNoOkTest() throws Exception {
            authenticationRequest=new JwtRequest();
            authenticationRequest.setUsername("nel@gmail.com");
            authenticationRequest.setPassword("12345678");
            User userSpring=null;
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            String requestJson=ow.writeValueAsString(authenticationRequest );
            given(userDetailsService.loadUserByUsername(authenticationRequest.getUsername())).willThrow(UsernameNotFoundException.class)   ;
            mvc.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(status().is4xxClientError());
            verify(userDetailsService, VerificationModeFactory.times(1)).loadUserByUsername(authenticationRequest.getUsername());
            reset(userDetailsService);
            verify(jwtTokenUtil, VerificationModeFactory.times(0)).generateToken(userSpring);
            reset(userDetailsService);
        }

    }

