//package ar.edu.unq.desapp.grupoD022021.backenddesappapi.integrationtest;
//
//import ar.edu.unq.desapp.grupoD022021.backenddesappapi.BackendDesappApiApplication;
//import ar.edu.unq.desapp.grupoD022021.backenddesappapi.integrationtest.TestConfig;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = TestConfig.class)
//@SpringBootTest(classes = BackendDesappApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class TestWithRestTemplate {
//
//    @LocalServerPort
//    Integer port;
//
//    @Autowired
//    TestRestTemplate restTemplate;
//
//    @Test
//    public void testCryptoassets() {
////        ResponseEntity<List> result =  (restTemplate.getForEntity("http://localhost:"+port+"/api/cryptoassets", List.class));
////        assertNotNull(result);
////
////        /**Chequeo que haya traido todos los crypto activos**/
////        assertEquals(14, result.getBody().size());
////
////        /**Chequear que sean solicitados **/
////        List<LinkedHashMap> cryptoassets = (result.getBody());
////        String aliceusdt = (String) cryptoassets.get(0).get("symbol");
////        assertEquals("ALICEUSDT", aliceusdt);
////
////        String MATICUSDT = (String) cryptoassets.get(1).get("symbol");
////        assertEquals("MATICUSDT", MATICUSDT);
////
////        String AUDIOUSDT = (String) cryptoassets.get(13).get("symbol");
////        assertEquals("AUDIOUSDT", AUDIOUSDT);
//
//    }
//}
