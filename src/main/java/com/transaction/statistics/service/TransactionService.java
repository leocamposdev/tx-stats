package com.transaction.statistics.service;

import com.transaction.statistics.exception.TooOldTransactionException;
import com.transaction.statistics.model.Transaction;
import com.transaction.statistics.model.TransactionStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TransactionService {

    private TransactionStore transactionStore;

    @Autowired
    public TransactionService(TransactionStore transactionStore){
        this.transactionStore = transactionStore;
    }

    public void addTransaction(Transaction transaction) throws TooOldTransactionException {
        if (transaction.getTimestamp() >= Instant.now().minusSeconds(60).toEpochMilli()) {
            new Thread(() ->  transactionStore.addTransaction(transaction)).start();
        } else {
            throw new TooOldTransactionException();
        }
    }
}
