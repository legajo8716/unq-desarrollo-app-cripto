package ar.edu.unq.desapp.grupoD022021.backenddesappapi.model;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.SpringTestConfig;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories.TransactionRepository;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = SpringTestConfig.class)
public class PointHandlerTest {

    @Autowired
    PointHandler pointHandler;
    @Mock
    Transaction transaction;
    @Mock
    User usuarioCompradorUpdate;
    @Mock
    User usuarioVendedorUpdate;


    @Test
    public void getPointConfirmTransactionOnExpired() throws InterruptedException {
        LocalDateTime hour =LocalDateTime.now();
        given(transaction.getHour()).willReturn(hour);
        TimeUnit.SECONDS.sleep(61);

        Assertions.assertEquals(pointHandler.getPointConfirmTransaction(transaction),5);

    }
    @Test
    public void getPointConfirmTransactionOnNotExpired() throws InterruptedException {
        LocalDateTime hour =LocalDateTime.now();
        given(transaction.getHour()).willReturn(hour);


        Assertions.assertEquals(pointHandler.getPointConfirmTransaction(transaction),10);

    }

    @Test
    public void getReputacionUser() throws InterruptedException {
        LocalDateTime hour =LocalDateTime.now();
        given(usuarioCompradorUpdate.getNumberOfOperations()).willReturn(2);
        given(usuarioCompradorUpdate.getAwardedPoints()).willReturn(2);
        Assertions.assertEquals(pointHandler.getReputacion(usuarioCompradorUpdate),1);

    }

}
