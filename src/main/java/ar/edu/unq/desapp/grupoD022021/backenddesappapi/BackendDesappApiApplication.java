package ar.edu.unq.desapp.grupoD022021.backenddesappapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class BackendDesappApiApplication extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

	/** Bean para poder usar apis publicas **/
	@Bean
	public RestTemplate getresttemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendDesappApiApplication.class, args);
	}

}
