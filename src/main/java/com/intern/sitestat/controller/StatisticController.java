package com.intern.sitestat.controller;

import com.intern.sitestat.model.RangeStatistic;
import com.intern.sitestat.model.Statistic;
import com.intern.sitestat.service.StatisticService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Date;

@RestController
@RequestMapping("statistic")
public class StatisticController {

    private final StatisticService statisticService;

    @Autowired
    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    /**
     * Method for adding the event in a storage and get a statistic.
     *
     * @param userId id of the user
     * @param pageId id of the page
     * @return the {@code Statistic} represents numbers of today's visits and unique users
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Creates event, returns today's statistic.", response = Statistic.class)
    public Statistic createEventAndGetStat(
        @ApiParam(required = true) @RequestParam @NotNull String userId,
        @ApiParam(required = true) @RequestParam @NotNull String pageId
    ) {
        return statisticService.addEventAndGetStat(userId, pageId);
    }

    /**
     * Method for getting the statistic in the specific range.
     *
     * @param begin the start date of the range
     * @param end   the end date of the range
     * @return the {@code RangeStatistic} represents numbers of visits, unique and permanent users in the specific range.
     */
    @GetMapping
    @ApiOperation(value = "Get the statistic in specific time range.", notes = "Date has following format: yyyy-MM-dd",
        response = RangeStatistic.class)
    public RangeStatistic getRangeStat(
        @ApiParam(required = true) @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date begin,
        @ApiParam(required = true) @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date end
    ) {
        return statisticService.getRangeStat(begin, end);
    }
}
