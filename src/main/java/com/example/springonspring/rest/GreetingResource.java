package com.example.springonspring.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.*;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;

@Slf4j
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

    @PostMapping
    @RequestMapping(path = HELLO_PATH + "/photo", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> greetByPhoto(@RequestPart("image") MultipartFile image) {
        log.info("Received file, name={}, size={}", image.getName(), image.getSize());
        processImage(image);
        return Collections.singletonMap("response", "Hi you're looking good!");
    }


    private void processImage(MultipartFile image) {
        try(BufferedReader data = new BufferedReader(new InputStreamReader(image.getInputStream()));
            FileWriter writer = new FileWriter(String.valueOf(Paths.get(".")))) {
            data.transferTo(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
