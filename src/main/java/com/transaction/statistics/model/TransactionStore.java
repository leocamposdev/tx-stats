package com.transaction.statistics.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class TransactionStore {

    private List<Transaction> transactions;

    public TransactionStore() {
        transactions = Collections.synchronizedList(new ArrayList<Transaction>());
    }

    public synchronized void addTransaction(Transaction tx) {
        transactions.add(tx);
    }

    public synchronized List<Transaction> getTransactions() {
        return transactions;
    }
}
