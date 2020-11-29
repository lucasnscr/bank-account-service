package br.com.bank.count.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("br.com.bank.count.controller"))
				.paths(PathSelectors.any()).build().useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, responseMessageForGET()).apiInfo(apiInfo());
	}

	private List<ResponseMessage> responseMessageForGET() {
		return new ArrayList<ResponseMessage>() {
			private static final long serialVersionUID = 1L;

			{
				add(new ResponseMessageBuilder().code(500).message("500 message").responseModel(new ModelRef("Error"))
						.build());
				add(new ResponseMessageBuilder().code(403).message("Forbidden!").build());
			}
		};
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Bank-Count-Transfer REST API")
				.description("Servico que realiza cadastro e transferencia de valores entre os clientes")
				.version("1.0.0").contact(new Contact("Lucas do Nascimento Silva", "https://www.linkedin.com/in/lucasnscr/",
						"l.nascimento.scr@gmail.com"))
				.build();
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}
