package org.pmf.services.office.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfigration.class})
@EntityScan({"org.pmf.services.office.*"})
@ComponentScan({"org.pmf.services.office.*"})
@PropertySources({
        @PropertySource("classpath:financial-request-application.properties"),
        @PropertySource(ignoreResourceNotFound=true,value="classpath:financial-request-application-${spring.profiles.active}.properties"),
        @PropertySource("classpath:order-package-application.properties"),
        @PropertySource(ignoreResourceNotFound=true,value="classpath:order-package-application-${spring.profiles.active}.properties"),
        @PropertySource("classpath:integration-application.properties"),
        @PropertySource(ignoreResourceNotFound=true,value="classpath:integration-application-${spring.profiles.active}.properties"),
        @PropertySource("classpath:application.properties")
})
@EnableSwagger2
public class officeServiceBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(officeServiceBootstrap.class, args);
    }
}
