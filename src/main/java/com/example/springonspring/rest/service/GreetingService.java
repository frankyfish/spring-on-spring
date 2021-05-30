package com.example.springonspring.rest.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

//@Service // !! should be places on an implementation class
public interface GreetingService {

    void processMultipart(MultipartFile file) throws IOException; // todo: add custom exception; handle in advice

}
