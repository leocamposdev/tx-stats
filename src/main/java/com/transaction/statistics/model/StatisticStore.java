package com.transaction.statistics.model;

import org.springframework.stereotype.Component;

@Component
public class StatisticStore {

    private Statistic statistic;

    public StatisticStore(){
        statistic = new Statistic();
    }

    public synchronized Statistic getStatistic() {
        return statistic;
    }

    public synchronized void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }
}
