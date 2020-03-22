package com.intern.sitestat.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.IdGenerator;
import org.springframework.util.JdkIdGenerator;

@Repository
public class StatisticRepositoryImpl implements StatisticRepository {

    private static final String SQL_ADD_EVENT = "INSERT INTO events VALUES (?, ?, ?)";
    private static final String SQL_GET_TODAY_VISITS_NUM = "SELECT COUNT(*) FROM events WHERE date > CURRENT_DATE";
    private static final String SQL_TODAYS_UNIQUE_USERS =
        "SELECT COUNT(DISTINCT user_id) FROM events WHERE date > CURRENT_DATE";
    private final JdbcTemplate jdbcTemplate;
    private final IdGenerator idGenerator = new JdkIdGenerator();

    @Autowired
    public StatisticRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEvent(String userId, String pageId) {
        jdbcTemplate.update(SQL_ADD_EVENT, idGenerator.generateId(), userId, pageId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTodaysVisitsNumber() {
        return jdbcTemplate.queryForObject(SQL_GET_TODAY_VISITS_NUM, Integer.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTodaysUniqueUsersNumber() {
        return jdbcTemplate.queryForObject(SQL_TODAYS_UNIQUE_USERS, Integer.class);
    }
}
