package com.example.springonspring.rest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@RestController
@Controller
@ResponseBody
//@RequestMapping(path = "/greet")
@RequestMapping(path = GreetingResource.BASE_PATH)
public class GreetingResource {

    public static final String BASE_PATH = "/greet";

    @GetMapping
    @RequestMapping(path = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public String greet() {
        return "Greetings fellow Stranger!";
    }

/*    @GetMapping
    @RequestMapping(path = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public String greet(Model model) {
        model.addAttribute("Greetings fellow Stranger!");
        return "hello";
    }*/

}
