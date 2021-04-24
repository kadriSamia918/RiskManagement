package com.telnet.project;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.amqp.core.Queue;
import com.telnet.project.ServiceImpl.StorageService;


import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableMongoRepositories ("com.telnet.project.Repository")
@EnableSwagger2
public class PfeQual2Application  extends SpringBootServletInitializer {
	  @Resource
	  StorageService storageService;
	public static void main(String[] args) {
		SpringApplication.run(PfeQual2Application.class, args);
	}
	public void run(String... args) throws Exception {
		 storageService.deleteAll();
		    storageService.init();
	}
	
	@Bean
	public Queue longRunning_process_progress_statusQueue() {
		return new Queue("long_running_process_progress_status");
	}
	 @Bean
	    public Docket apiDocket() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.any())
	                .paths(PathSelectors.any())
	                .build()
	                .apiInfo(apiDetails());
	    }
	 private ApiInfo apiDetails() {
		 return new ApiInfo("Gestion des risques","Sana LAADHAR ", null, null, null, null, null);
	 }
	 @Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		 return builder.sources(PfeQual2Application.class);
		 
	 }
}
