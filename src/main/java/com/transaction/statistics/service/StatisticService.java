package com.transaction.statistics.service;

import com.transaction.statistics.model.Statistic;
import com.transaction.statistics.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class StatisticService {

    public Statistic getStatistic() {

        Instant instant = Instant.now();
        instant.toEpochMilli();

        return null;

    }


}
