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
    @Around(value = "execution(* ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.TransactionService.getAllTransaction())")
public void getServicesExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        long endTime = System.currentTimeMillis() - startTime;

        log.info("Nombre del metodo:" +proceedingJoinPoint.getSignature().getName());

        log.info("Tiempo de ejecucion: "+endTime+" ms");




}
}
