package com.transaction.statistics.process;

import com.transaction.statistics.model.Statistic;
import com.transaction.statistics.model.StatisticStore;
import com.transaction.statistics.model.Transaction;
import com.transaction.statistics.model.TransactionStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;

@Component
public class AggregatorProcess implements Runnable {

    private StatisticStore statisticStore;
    private TransactionStore transactionStore;

    @Autowired
    public AggregatorProcess(TransactionStore transactionStore, StatisticStore statisticStore) {
        this.transactionStore = transactionStore;
        this.statisticStore = statisticStore;
    }

    @Override
    public void run() {
        Statistic statistic = new Statistic();
        try {
            while (true) {

                Thread.sleep(10);

                DoubleSummaryStatistics summary;
                synchronized (transactionStore.getTransactions()) {
                    summary = transactionStore.getTransactions()
                                    .parallelStream()
                                    .filter(t -> t.getTimestamp() >= sixtySecondsAgo())
                                    .collect(Collectors.summarizingDouble(Transaction::getAmount));
                }

                statistic.setCount(summary.getCount());
                statistic.setSum(summary.getSum());
                statistic.setMin(summary.getMin());
                statistic.setMax(summary.getMax());
                statistic.setAvg(summary.getAverage());

                statisticStore.setStatistic(statistic);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private long sixtySecondsAgo() {
        return Instant.now().minusSeconds(60).toEpochMilli();
    }

}
