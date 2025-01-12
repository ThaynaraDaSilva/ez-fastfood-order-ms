package br.com.fiap.ez.fastfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

import br.com.fiap.ez.fastfood.infrastructure.config.HttpClientProperties;

@SpringBootApplication
//(exclude = { SecurityAutoConfiguration.class })
@EnableFeignClients
//@EnableConfigurationProperties(HttpClientProperties.class)
public class ApiApplication {
	
	  public static void main(String[] args) {
	        SpringApplication.run(ApiApplication.class, args);
	    }

}
