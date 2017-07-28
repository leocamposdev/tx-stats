package com.transaction.statistics.endpoint;

import com.google.gson.Gson;
import com.transaction.statistics.Application;
import com.transaction.statistics.model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@AutoConfigureMockMvc
public class EndpointsConcurrencyTest {

    @Autowired
    private MockMvc mvc;

    private Gson gson = new Gson();

    @Test
    public void shouldPostTransactions() throws Exception {

        for (int i = 1; i <= 500; i++) {
            String json = gson.toJson(new Transaction(Instant.now().toEpochMilli(), i));

            mvc.perform(MockMvcRequestBuilders
                    .post("/transactions")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(json))
                    .andExpect(status().isOk());
        }
    }

    @Test
    public void shouldGetStatistics() throws Exception {

        for (int i = 1; i <= 500; i++) {
            Thread.sleep(10);
            mvc.perform(MockMvcRequestBuilders
                    .get("/statistics")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }

    }
}
