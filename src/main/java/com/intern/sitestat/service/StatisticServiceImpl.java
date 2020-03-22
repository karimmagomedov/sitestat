package com.intern.sitestat.service;

import com.intern.sitestat.model.RangeStatistic;
import com.intern.sitestat.model.Statistic;
import com.intern.sitestat.repository.RangeStatisticRepository;
import com.intern.sitestat.repository.StatisticRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StatisticServiceImpl implements StatisticService {

    private StatisticRepository statisticRepository;
    private RangeStatisticRepository rangeStatisticRepository;
    private static final Logger log = LoggerFactory.getLogger(StatisticServiceImpl.class);

    @Autowired
    public StatisticServiceImpl(StatisticRepository statisticRepository,
        RangeStatisticRepository rangeStatisticRepository) {
        this.statisticRepository = statisticRepository;
        this.rangeStatisticRepository = rangeStatisticRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Statistic addEventAndGetStat(String userId, String pageId) {
        statisticRepository.addEvent(userId, pageId);
        return new Statistic(statisticRepository.getTodaysVisitsNumber(),
            statisticRepository.getTodaysUniqueUsersNumber());
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalArgumentException if the end date is invalid
     */
    @Override
    public RangeStatistic getRangeStat(Date begin, Date end) {
        if (end.before(begin)) {
            String message = "Please, type valid the end date.";
            log.error(message);
            throw new IllegalArgumentException(message);
        }
        return new RangeStatistic(rangeStatisticRepository.getRangeVisitsNumber(begin, end),
            rangeStatisticRepository.getRangeUniqueUsersNumber(begin, end),
            rangeStatisticRepository.getPermanentUsersNumber(begin, end));
    }
}
