package com.example.springonspring.rest;

import com.example.springonspring.rest.service.GreetingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfig {

    // todo: check which way of creating RestController bean is preferable
//    @Bean
//    GreetingResource greetingResource(GreetingService greetingService) {
//        return new GreetingResource(greetingService);
//    }
}
