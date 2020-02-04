package com.antra.phonepad.combination;

import com.antra.phonepad.combination.vo.NumberRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CombinationIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getTest() throws Exception{
        this.mockMvc.perform(get("/phonePad/test")).andExpect(status().isOk())
                .andExpect(content().string(containsString("OK")));
    }

    @Test
    public void getCombinationTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        this.mockMvc.perform(post("/phonePad/combination").content(mapper.writeValueAsString(new NumberRequest("217","8198","210")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("message").value("Has Valid Combination"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getCombinationExceptionTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        this.mockMvc.perform(post("/phonePad/combination").content(mapper.writeValueAsString(new NumberRequest("217","81","2")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").value("This is not a valid phone number."))
                .andDo(MockMvcResultHandlers.print());
    }
}
