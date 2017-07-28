package com.transaction.statistics.endpoint;

import com.transaction.statistics.model.Statistic;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticEndpoint {

    @RequestMapping(value = "/statistics", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Statistic getStatistic() {
        return new Statistic();

    }

}
