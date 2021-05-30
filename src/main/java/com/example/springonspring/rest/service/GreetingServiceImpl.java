package com.example.springonspring.rest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@Slf4j
@Service
public class GreetingServiceImpl implements GreetingService {
    @Override
    public void processMultipart(MultipartFile file) throws IOException {
        String fileName = Paths.get(".").toRealPath() + "/" + "file" + System.currentTimeMillis() + ".jpeg";
        log.debug("Storing file @ {}", fileName);
        file.transferTo(new File(fileName));
    }
}
