package com.example.springonspring.rest;

import com.example.springonspring.rest.exceptions.GreetingException;
import com.example.springonspring.rest.exceptions.InvalidGreetingContentValueException;
import com.example.springonspring.rest.exceptions.MistakeGreetingException;
import com.example.springonspring.rest.service.GreetingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Slf4j
@Validated
@RestController // combines: @Controller, @ResponseBody
@RequestMapping(path = GreetingResource.BASE_PATH)
public class GreetingResource {

    static final String BASE_PATH = "/greet";
    static final String HELLO_PATH = "/hello";
    static final String BAD_PATH = "/mistake";

    private final GreetingService greetingService;

    public GreetingResource(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping
    @RequestMapping(path = HELLO_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public String greet() {
        return "Greetings fellow Stranger!"; // returns plain string, not a JSON
    }
    //https://www.baeldung.com/javax-validation
    @GetMapping
    @RequestMapping(path = HELLO_PATH + "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> greet(@Size(min = 1, max = 5) @PathVariable String name) {
        if (StringUtils.hasLength(name)) { // fixme: mb move out this logic somewhere else
            throw new InvalidGreetingContentValueException("Name shouldn't be null");
        }
        return Collections.singletonMap("response", String.format("Hi %s !", name));
    }

    /**
     * Handles multipart body with a file that supposed to be a picture.
     * With default SpringBoot config file size is limited to:
     * The field image exceeds its maximum permitted size of 1048576 bytes.
     * @param image multipart file; image
     * @throws org.springframework.web.multipart.MaxUploadSizeExceededException that is wrapped around
     *   org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException
     * @return constant value that hopefully makes one feel better
     */
    @PostMapping
    @RequestMapping(path = HELLO_PATH + "/photo", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> greetByPhoto(@RequestPart("image") MultipartFile image) {
        log.info("Received file, name={}, size={}", image.getName(), image.getSize());
        try {
            greetingService.processMultipart(image);
        } catch (IOException e) {
            log.error("Failed to process multipart request", e);
        }
        return Collections.singletonMap("response", "Hi you're looking good!");
    }

    @GetMapping
    @RequestMapping(path = BAD_PATH)
    public String fail() {
        throw new MistakeGreetingException("You've moved aside from the route.");
    }

}
