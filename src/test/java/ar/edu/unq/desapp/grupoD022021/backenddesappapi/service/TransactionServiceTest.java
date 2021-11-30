package ar.edu.unq.desapp.grupoD022021.backenddesappapi.service;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.SpringTestConfig;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto.TransactionDTO;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.PointHandler;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories.TransactionRepository;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = SpringTestConfig.class)
public class TransactionServiceTest {
   Transaction transaction;
    User usuarioCompradorUpdate;
    User usuarioVendedorUpdate;
    @Autowired
    TransactionService transactionService;
    @MockBean
    PointHandler pointHandler;
    @MockBean
    TransactionRepository transactionRepository;

    @Before
    public void setUp() throws Exception {
        transaction=new Transaction();
        transaction.setId(1);
        usuarioCompradorUpdate=new User();
        usuarioVendedorUpdate=new User();
        transaction.setUsuarioComprador(usuarioCompradorUpdate);
        transaction.setUsuarioVendedor(usuarioVendedorUpdate);
    }

    @Test
    public void confirmTransactionOnNotExpired() {
        given(transactionRepository.findById(transaction.getId())).willReturn(transaction);
        given(pointHandler.getPointConfirmTransaction(transaction)).willReturn(10);
        given(pointHandler.getReputacion(usuarioCompradorUpdate)).willReturn(10.0);
        given(pointHandler.getReputacion(usuarioVendedorUpdate)).willReturn(10.0);
        given(transactionRepository.save(transaction)).willReturn(transaction);
        transactionService.transactionConfirmation(transaction.getId());
        Assertions.assertEquals(usuarioCompradorUpdate.getAwardedPoints(),10);
        Assertions.assertEquals(usuarioVendedorUpdate.getAwardedPoints(),10);
        Assertions.assertEquals(usuarioVendedorUpdate.getNumberOfOperations(),1);
        Assertions.assertEquals(usuarioCompradorUpdate.getNumberOfOperations(),1);

    }
    @Test
    public void confirmTransactionOnExpired() {
        given(transactionRepository.findById(transaction.getId())).willReturn(transaction);
        given(pointHandler.getPointConfirmTransaction(transaction)).willReturn(5);
        given(pointHandler.getReputacion(usuarioCompradorUpdate)).willReturn(5.0);
        given(pointHandler.getReputacion(usuarioVendedorUpdate)).willReturn(5.0);
        given(transactionRepository.save(transaction)).willReturn(transaction);
        transactionService.transactionConfirmation(transaction.getId());
        Assertions.assertEquals(usuarioCompradorUpdate.getAwardedPoints(),5);
        Assertions.assertEquals(usuarioVendedorUpdate.getAwardedPoints(),5);
        Assertions.assertEquals(usuarioVendedorUpdate.getNumberOfOperations(),1);
        Assertions.assertEquals(usuarioCompradorUpdate.getNumberOfOperations(),1);

    }
    @Test
    public void cancellTransaction() {
        given(transactionRepository.findById(transaction.getId())).willReturn(transaction);
        given(pointHandler.getPointConfirmTransaction(transaction)).willReturn(0);
        given(pointHandler.getReputacion(usuarioCompradorUpdate)).willReturn(0.0);
        given(pointHandler.getReputacion(usuarioVendedorUpdate)).willReturn(0.0);
        given(transactionRepository.save(transaction)).willReturn(transaction);
        transactionService.transactionCancell(transaction.getId());
        Assertions.assertEquals(usuarioCompradorUpdate.getAwardedPoints(),0);
        Assertions.assertEquals(usuarioVendedorUpdate.getAwardedPoints(),0);
        Assertions.assertEquals(usuarioVendedorUpdate.getNumberOfOperations(),1);
        Assertions.assertEquals(usuarioCompradorUpdate.getNumberOfOperations(),1);

    }



}
