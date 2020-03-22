package com.intern.sitestat.service;

import com.intern.sitestat.model.RangeStatistic;
import com.intern.sitestat.model.Statistic;

import java.util.Date;

/**
 * The interface provides opportunity for adding the data in a storage and getting different statistic.
 */
public interface StatisticService {

    /**
     * The purpose of this method is the same as {@link com.intern.sitestat.controller.StatisticController#createEventAndGetStat(String,
     * String)}.
     */
    Statistic addEventAndGetStat(String userId, String pageId);

    /**
     * The purpose of this method is the same as {@link com.intern.sitestat.controller.StatisticController#getRangeStat(Date,
     * Date)}.
     */
    RangeStatistic getRangeStat(Date begin, Date end);

}
