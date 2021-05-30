package com.example.springonspring.rest;

import com.example.springonspring.rest.service.GreetingServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class GreetingServiceImplTest {

    private final GreetingServiceImpl toTest = new GreetingServiceImpl();

    @Test
    public void processesMultipart() throws IOException {
        MultipartFile file = Mockito.mock(MultipartFile.class);
        toTest.processMultipart(file);
        Mockito.verify(file, Mockito.times(1)).transferTo(Mockito.any(File.class));
    }

}
