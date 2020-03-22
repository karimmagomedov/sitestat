package com.intern.sitestat.service;

import com.intern.sitestat.model.Statistic;
import com.intern.sitestat.repository.RangeStatisticRepository;
import com.intern.sitestat.repository.StatisticRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class StatisticServiceImplTest {

    @Mock
    private StatisticRepository statisticRepository;
    @Mock
    private RangeStatisticRepository rangeStatisticRepository;

    private static final String TEST_USER_ID = "userIdTest1";
    private static final String TEST_PAGE_ID = "pageIdTest1";
    private static final int EXPECTED_UNIQUE_USERS = 10;
    private static final int EXPECTED_VISITS = 20;

    private StatisticServiceImpl statisticService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        statisticService = new StatisticServiceImpl(statisticRepository, rangeStatisticRepository);
    }

    @Test
    void addEventAndGetStat() {
        doNothing().when(statisticRepository).addEvent(TEST_USER_ID, TEST_PAGE_ID);
        when(statisticRepository.getTodaysUniqueUsersNumber()).thenReturn(EXPECTED_UNIQUE_USERS);
        when(statisticRepository.getTodaysVisitsNumber()).thenReturn(EXPECTED_VISITS);

        Statistic statistic = statisticService.addEventAndGetStat(TEST_USER_ID, TEST_PAGE_ID);

        assertEquals(statistic.getTodaysVisitsNumber(), EXPECTED_VISITS);
        assertEquals(statistic.getTodaysUniqueUsersNumber(), EXPECTED_UNIQUE_USERS);
    }
}
