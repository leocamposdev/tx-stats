package com.transaction.statistics.service;

import com.transaction.statistics.model.Statistic;
import com.transaction.statistics.model.StatisticStore;
import com.transaction.statistics.model.Transaction;
import com.transaction.statistics.model.TransactionStore;
import com.transaction.statistics.process.AggregatorProcess;
import com.transaction.statistics.process.CleanupProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.Date;

@Service
public class StatisticService {

    private TransactionStore transactionStore;
    private StatisticStore statisticStore;

    @Autowired
    public StatisticService(TransactionStore transactionStore, StatisticStore statisticStore){
        this.transactionStore = transactionStore;
        this.statisticStore = statisticStore;
    }

    public Statistic getStatistic(){
        return statisticStore.getStatistic();
    }

    @PostConstruct
    public void startBackgroundWorkers(){
        Thread aggregator = new Thread(new AggregatorProcess(transactionStore, statisticStore));
        aggregator.start();

        Thread cleanup = new Thread(new CleanupProcess(transactionStore));
        cleanup.start();
   }

}
