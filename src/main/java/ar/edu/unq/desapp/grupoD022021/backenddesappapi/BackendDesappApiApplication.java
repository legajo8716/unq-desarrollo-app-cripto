package ar.edu.unq.desapp.grupoD022021.backenddesappapi;

import ar.edu.unq.desapp.grupoD022021.backenddesappapi.utility.CreateDatosFake;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAspectJAutoProxy
@Slf4j

@EnableCaching
public class BackendDesappApiApplication  {



	/** Bean para poder usar apis publicas **/
	@Bean
	public RestTemplate getresttemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {






		SpringApplication.run(BackendDesappApiApplication.class, args);
	}

}
