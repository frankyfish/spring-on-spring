package com.example.springonspring.rest;

import com.example.springonspring.rest.service.GreetingService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class GreetingResourceIT {

    @MockBean
    GreetingService greetingService;

    @Test
    void respondsCorrectly() throws Exception {
        GreetingResource greetingResource = new GreetingResource(greetingService);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(greetingResource).build();

        mockMvc.perform(MockMvcRequestBuilders.get(GreetingResource.BASE_PATH + GreetingResource.HELLO_PATH))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(jsonPath("$").value("Greetings fellow Stranger!"));
    }

    @Test
    void doesntAcceptEmptyName() throws Exception {
        GreetingResource greetingResource = new GreetingResource(greetingService);
        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(greetingResource)
                .setControllerAdvice(GreetingAdvice.class)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.get(GreetingResource.BASE_PATH + GreetingResource.HELLO_PATH
                                                           + "/ "))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(jsonPath("$").value("Name shouldn't be null"))
               .andExpect(status().is(400));
    }

}
