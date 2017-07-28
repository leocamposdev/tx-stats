package com.transaction.statistics.process;

import com.transaction.statistics.model.Transaction;
import com.transaction.statistics.model.TransactionStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CleanupProcess implements Runnable {

    private TransactionStore transactionStore;

    @Autowired
    public CleanupProcess(TransactionStore transactionStore) {
        this.transactionStore = transactionStore;
    }

    @Override
    public void run() {

        try {
            while (true) {

                Thread.sleep(500);

                synchronized (transactionStore.getTransactions()) {

                    List<Transaction> tooOldTransactions = transactionStore.getTransactions()
                            .parallelStream()
                            .filter(t -> t.getTimestamp() < sixtySecondsAgo())
                            .collect(Collectors.toList());

                    transactionStore.getTransactions().removeAll(tooOldTransactions);
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private long sixtySecondsAgo() {
        return Instant.now().minusSeconds(60).toEpochMilli();
    }
}
