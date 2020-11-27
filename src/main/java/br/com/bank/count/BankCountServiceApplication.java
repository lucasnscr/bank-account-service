package br.com.bank.count;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import br.com.bank.count.config.SwaggerConfig;
@ComponentScan(basePackages = { "br.com.bank.count" })
@ComponentScan(basePackageClasses = {BankCountServiceApplication.class, SwaggerConfig.class})
@SpringBootApplication
public class BankCountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankCountServiceApplication.class, args);
	}

}
