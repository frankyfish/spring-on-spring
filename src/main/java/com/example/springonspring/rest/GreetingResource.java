package com.example.springonspring.rest;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.Map;

@Validated
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
    //https://www.baeldung.com/javax-validation
    @GetMapping
    @RequestMapping(path = HELLO_PATH + "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> greet(@Size(min = 1, max = 5) @PathVariable String name) {
        return Collections.singletonMap("response", String.format("Hi %s !", name));
    }

/*    @GetMapping
    @RequestMapping(path = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public String greet(Model model) {
        model.addAttribute("Greetings fellow Stranger!");
        return "hello";
    }*/

}
