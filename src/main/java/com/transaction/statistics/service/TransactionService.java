package com.transaction.statistics.service;

import com.transaction.statistics.exception.TooOldTransactionException;
import com.transaction.statistics.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TransactionService {
    private static long SIXTY_SECONDS = 60000;

    public void addTransaction(Transaction transaction) throws TooOldTransactionException {

        Instant instant = Instant.now();
        long now = instant.toEpochMilli();
        if (transaction.getTimestamp() < now - SIXTY_SECONDS)
            throw new TooOldTransactionException();




    }


}
