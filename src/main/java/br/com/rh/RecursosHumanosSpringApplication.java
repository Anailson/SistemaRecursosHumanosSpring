package br.com.rh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "br.com.rh.model")
public class RecursosHumanosSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecursosHumanosSpringApplication.class, args);
	}

}
