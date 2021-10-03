package ar.edu.unq.desapp.grupoD022021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Cryptoactive;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.CryptoactiveService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CryptoactiveControllerTest {

    @Autowired
    private MockMvc mvc;


    @MockBean
    CryptoactiveService cryptoactiveService;



    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getCryptoassertsTest() throws Exception {


        Cryptoactive cryp1 = new Cryptoactive("ALICEUSDT", "1.4");
        Cryptoactive cryp2 = new Cryptoactive("MATICUSDT", "1.4");
        Cryptoactive cryp3 = new Cryptoactive("AXSUSDT", "1.4");
        Cryptoactive cryp4 = new Cryptoactive("AAVEUSDT", "1.4");
        Cryptoactive cryp5 = new Cryptoactive("ATOMUSDT", "1.4");
        Cryptoactive cryp6 = new Cryptoactive("NEOUSDT", "1.4");
        Cryptoactive cryp7 = new Cryptoactive("DOTUSDT", "1.4");
        Cryptoactive cryp8 = new Cryptoactive("ETHUSDT", "1.4");
        Cryptoactive cryp9 = new Cryptoactive("CAKEUSDT", "1.4");
        Cryptoactive cryp10 = new Cryptoactive("BTCUSDT", "1.4");
        Cryptoactive cryp11 = new Cryptoactive("BNBUSDT", "1.4");
        Cryptoactive cryp12 = new Cryptoactive("ADAUSDT", "1.4");
        Cryptoactive cryp13 = new Cryptoactive("TRXUSDT", "1.4");
        Cryptoactive cryp14 = new Cryptoactive("AUDIOUSDT", "1.4");

        List<Cryptoactive> mocklist = new ArrayList<>();
        mocklist.add(cryp1);
        mocklist.add(cryp2);
        mocklist.add(cryp3);
        mocklist.add(cryp4);
        mocklist.add(cryp5);
        mocklist.add(cryp6);
        mocklist.add(cryp7);
        mocklist.add(cryp8);
        mocklist.add(cryp9);
        mocklist.add(cryp10);
        mocklist.add(cryp11);
        mocklist.add(cryp12);
        mocklist.add(cryp13);
        mocklist.add(cryp14);

        given(cryptoactiveService.getAllCryptoassets()).willReturn(mocklist);

        mvc.perform(get("/api/cryptoassets")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(14)));

        verify(cryptoactiveService, VerificationModeFactory.times(1)).getAllCryptoassets();
        reset(cryptoactiveService);
    }
}
