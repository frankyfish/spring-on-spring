package com.example.springonspring.rest;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class GreetingResourceTest {

    @Test
    public void respondsCorrectly() throws Exception {
        GreetingResource greetingResource = new GreetingResource();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(greetingResource).build();

        mockMvc.perform(MockMvcRequestBuilders.get(GreetingResource.BASE_PATH + "/hello"))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(jsonPath("$").value("Greetings fellow Stranger!"));
    }

}
