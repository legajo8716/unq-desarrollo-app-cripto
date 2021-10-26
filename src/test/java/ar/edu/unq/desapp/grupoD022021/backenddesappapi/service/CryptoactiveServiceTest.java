package ar.edu.unq.desapp.grupoD022021.backenddesappapi.service;


import ar.edu.unq.desapp.grupoD022021.backenddesappapi.BackendDesappApiApplication;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.SpringTestConfig;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Cryptoactive;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.DollarPrice;
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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;



import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = SpringTestConfig.class)
public class CryptoactiveServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(CryptoactiveServiceTest.class);

    @Autowired
    private CryptoactiveService cryptoactiveService;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    private ObjectMapper mapper = JsonMapper.builder()
            .findAndAddModules()
            .build();;


    @Before
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void getAllCryptoassetsTest() throws Exception {

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

        List<DollarPrice> dollarPriceListMock = new ArrayList<>();
        DollarPrice dollarPrice1 = new DollarPrice( "2.0");
        DollarPrice dollarPrice2 = new DollarPrice( "2.0");
        dollarPriceListMock.add(dollarPrice1);
        dollarPriceListMock.add(dollarPrice2);

        mockServer.expect(ExpectedCount.once(),
                        requestTo(new URI("https://www.dolarsi.com/api/api.php?type=valoresprincipales")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(dollarPriceListMock)));



        mockServer.expect(ExpectedCount.once(),
                        requestTo(new URI("https://api.binance.com/api/v3/ticker/price")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(mocklist)));



        List<Cryptoactive> result = cryptoactiveService.getAllCryptoassets();
        mockServer.verify();
        Assert.assertEquals(result.size(), 14 );
    }
}