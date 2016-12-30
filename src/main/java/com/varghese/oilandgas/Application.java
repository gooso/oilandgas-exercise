package com.varghese.oilandgas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by newuser on 12/12/16.
 */
@SpringBootApplication
@ImportResource("spring-context.xml")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
