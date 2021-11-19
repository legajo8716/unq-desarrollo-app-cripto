package ar.edu.unq.desapp.grupoD022021.backenddesappapi.aspect;



import ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto.TransactionDTO;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Transaction;
import io.netty.util.internal.logging.Log4J2LoggerFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.sql.SQLSyntaxErrorException;
import java.util.List;

@Slf4j

@Aspect
@Component
public class ActivityAspect {
    @AfterReturning(value = "execution(* ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.TransactionService.getAllTransaction())",returning = "persons")
public void getServicesExecutionTime(List<TransactionDTO> persons) {
        System.out.println("acaaaaaaaaaaaaaaaaaaaaaaaaa");
        log.info("");
}
}
