package com.example.springonspring.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.Map;

@RestController // combines: @Controller, @ResponseBody
@RequestMapping(path = GreetingResource.BASE_PATH)
public class GreetingResource {

     static final String BASE_PATH = "/greet";
     static final String HELLO_PATH = "/hello";

    @GetMapping
    @RequestMapping(path = HELLO_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String greet() {
        return "Greetings fellow Stranger!"; // returns plain string, not a JSON
    }

    @GetMapping
    @RequestMapping(path = HELLO_PATH + "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> greet(@PathVariable @NotBlank String name) { // todo: check why's not working
        // receive a JSON as a response; otherwise it is a simple string
        return Collections.singletonMap("response", String.format("Hi %s !", name));
    }

/*    @GetMapping
    @RequestMapping(path = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public String greet(Model model) {
        model.addAttribute("Greetings fellow Stranger!");
        return "hello";
    }*/

}
