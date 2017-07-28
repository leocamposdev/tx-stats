package com.transaction.statistics.endpoint;

import com.transaction.statistics.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@AutoConfigureMockMvc
public class StatisticEndpointTest {

    @Autowired
    private MockMvc mvc;


    @Test
    public void shouldGenerateStatistics() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/statistics")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}