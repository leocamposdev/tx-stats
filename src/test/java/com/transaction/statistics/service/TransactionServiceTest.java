package com.transaction.statistics.service;

import com.transaction.statistics.exception.TooOldTransactionException;
import com.transaction.statistics.model.Transaction;
import com.transaction.statistics.model.TransactionStore;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;

public class TransactionServiceTest {

    private TransactionStore transactionStore;

    @Before
    public void setUp(){
        transactionStore = new TransactionStore();
    }

    @Test(expected = TooOldTransactionException.class)
    public void shouldThrowExceptionForTooOldTransaction() throws Exception {
        TransactionService transactionService = new TransactionService(transactionStore);
        Transaction tx = new Transaction(Instant.now().toEpochMilli()-60001, 1);
        transactionService.addTransaction(tx);
    }
}
