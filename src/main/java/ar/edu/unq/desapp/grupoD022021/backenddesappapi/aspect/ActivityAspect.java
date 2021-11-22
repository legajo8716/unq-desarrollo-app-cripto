package ar.edu.unq.desapp.grupoD022021.backenddesappapi.aspect;



import ar.edu.unq.desapp.grupoD022021.backenddesappapi.dto.TransactionDTO;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.repositories.UserRepository;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.security.jwt.JwtRequest;
import ar.edu.unq.desapp.grupoD022021.backenddesappapi.security.jwt.JwtResponse;
import io.netty.util.internal.logging.Log4J2LoggerFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.sql.SQLSyntaxErrorException;
import java.util.Arrays;
import java.util.List;

@Slf4j

@Aspect
@Component
public class ActivityAspect {
    @Autowired
        UserRepository userRepository;

    @Around(value = "execution(* ar.edu.unq.desapp.grupoD022021.backenddesappapi.service.*.*(..))")
public Object getServicesExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
       Object result= proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        long endTime = System.currentTimeMillis() - startTime;

        if (proceedingJoinPoint.getArgs().length<0){
            log.info("El metodo no tiene parametros");
        }
        else{
            int nroParametro=0;
            for  (Object param : proceedingJoinPoint.getArgs()){
                ;
                log.info("Parametro "+ nroParametro+": "+param.toString());
            }
        }

        log.info("Nombre del metodo: " +proceedingJoinPoint.getSignature().getName());

        log.info("Tiempo de ejecucion: "+endTime+" ms");
        log.info("                                                                ");

    return result;



}
}
