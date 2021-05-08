package com.example.springonspring.rest;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class GreetingResourceTest {

    @Test
    void respondsCorrectly() throws Exception {
        GreetingResource greetingResource = new GreetingResource();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(greetingResource).build();

        mockMvc.perform(MockMvcRequestBuilders.get(GreetingResource.BASE_PATH + GreetingResource.HELLO_PATH))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(jsonPath("$").value("Greetings fellow Stranger!"));
    }

    @Test
    void doesntAcceptEmptyName() throws Exception {
        GreetingResource greetingResource = new GreetingResource();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(greetingResource).build();

        mockMvc.perform(MockMvcRequestBuilders.get(GreetingResource.BASE_PATH + GreetingResource.HELLO_PATH
                + "/ "))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$").value("Hi buddy !"));
    }

}
