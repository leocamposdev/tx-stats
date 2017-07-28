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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@AutoConfigureMockMvc
public class TransactionEndpointTest {

    @Autowired
    private MockMvc mvc;

    private Gson gson = new Gson();

    @Test
    public void shouldAddTransaction() throws Exception {
        String json = gson.toJson(new Transaction(Instant.now().toEpochMilli(), 11.2));

        mvc.perform(MockMvcRequestBuilders
                .post("/transactions")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotAddTooOldTransaction() throws Exception {
        String json = gson.toJson(new Transaction(Instant.now().minusSeconds(61).toEpochMilli(), 11.2));

        mvc.perform(MockMvcRequestBuilders
                .post("/transactions")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isNoContent());
    }

}
