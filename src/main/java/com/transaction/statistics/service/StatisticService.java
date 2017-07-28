package com.transaction.statistics.service;

import com.transaction.statistics.model.Statistic;
import com.transaction.statistics.model.Transaction;
import com.transaction.statistics.model.TransactionStore;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class StatisticService {

    private TransactionStore transactionStore;

    public StatisticService(TransactionStore transactionStore){
        this.transactionStore = transactionStore;
    }

    public Statistic getStatistic() {
        return transactionStore.getStatistics();
    }


}
