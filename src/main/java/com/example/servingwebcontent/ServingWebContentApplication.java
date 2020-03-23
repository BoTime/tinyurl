package com.example.servingwebcontent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/** 
 * hot reload:
 * https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto-hotswapping
 * https://medium.com/@gustavo.ponce.ch/spring-boot-spring-mvc-spring-security-mysql-a5d8545d837d
 * https://spring.io/guides/gs/rest-service/
 * 
*/
@SpringBootApplication
public class ServingWebContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServingWebContentApplication.class, args);
    }

}
