package com.transaction.statistics.endpoint;

import com.transaction.statistics.model.Statistic;
import com.transaction.statistics.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticEndpoint {

    private StatisticService statisticService;

    @Autowired
    public StatisticEndpoint(StatisticService statisticService){
        this.statisticService = statisticService;
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Statistic> getStatistic() {

        return ResponseEntity.ok(statisticService.getStatistic());

    }

}
