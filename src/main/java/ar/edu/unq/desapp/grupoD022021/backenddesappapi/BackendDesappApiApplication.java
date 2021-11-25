package ar.edu.unq.desapp.grupoD022021.backenddesappapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAspectJAutoProxy

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
