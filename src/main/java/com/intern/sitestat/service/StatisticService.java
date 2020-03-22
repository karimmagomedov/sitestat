package com.intern.sitestat.service;

import com.intern.sitestat.model.RangeStatistic;
import com.intern.sitestat.model.Statistic;

import java.util.Date;

public interface StatisticService {

    Statistic addEventAndGetStat(String userId, String pageId);

    RangeStatistic getRangeStat(Date begin, Date end);

}
