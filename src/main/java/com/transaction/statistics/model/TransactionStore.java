package com.transaction.statistics.model;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionStore {

    private List<Transaction> transactions;

    public TransactionStore() {
        transactions = Collections.synchronizedList(new ArrayList<>());
    }

    public synchronized void addTransaction(Transaction tx){
        transactions.add(tx);
    }

    public synchronized Statistic getStatistics() {
        Statistic statistic = new Statistic();
        final

        DoubleSummaryStatistics summary = transactions.parallelStream()
                .filter(t -> t.getTimestamp() >= sixtySecondsAgo())
                .collect(Collectors.summarizingDouble(Transaction::getAmount));

       statistic.setCount(summary.getCount());
       statistic.setSum(summary.getSum());
       statistic.setMin(summary.getMin());
       statistic.setMax(summary.getMax());
       statistic.setAvg(summary.getAverage());
       return  statistic;
    }

    private long sixtySecondsAgo(){
        return Instant.now().minusSeconds(60).toEpochMilli();
    }


}
