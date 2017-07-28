package com.transaction.statistics.service;

import com.transaction.statistics.exception.TooOldTransactionException;
import com.transaction.statistics.model.Transaction;
import com.transaction.statistics.model.TransactionStore;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;

public class ServiceTest {

    private TransactionStore transactionStore;

    @Before
    public void setUp(){
        transactionStore = new TransactionStore();
    }

    @Test(expected = TooOldTransactionException.class)
    public void shouldThrowExceptionForTooOldTransaction() throws Exception {
        TransactionService transactionService = new TransactionService(transactionStore);
        Transaction tx = new Transaction();
        tx.setAmount(12.1);
        tx.setTimestamp(Instant.now().toEpochMilli()-60001);
        transactionService.addTransaction(tx);
    }

    @Test
    public void shouldGenerateStatistics() throws Exception {



    }

}
