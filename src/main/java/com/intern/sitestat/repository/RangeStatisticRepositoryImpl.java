package com.intern.sitestat.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class RangeStatisticRepositoryImpl implements RangeStatisticRepository {

    private static final String SQL_RANGE_VISITS_NUMBER =
        "SELECT COUNT(*) FROM events WHERE date BETWEEN ? AND ?;";
    private static final String SQL_RANGE_UNIQUE_USERS_NUMBER =
        "SELECT COUNT(DISTINCT user_id) FROM events WHERE date BETWEEN ? AND ?;";
    private static final String SQL_PERMANENT_USERS_NUMBER =
        "SELECT COUNT(*) FROM (SELECT COUNT(*) AS COUNT FROM events WHERE date BETWEEN ? AND ? GROUP BY user_id HAVING COUNT(*) > 10) AS resultTable;";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RangeStatisticRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRangeVisitsNumber(Date begin, Date end) {
        return jdbcTemplate.queryForObject(SQL_RANGE_VISITS_NUMBER, new Object[]{begin, end}, Integer.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRangeUniqueUsersNumber(Date begin, Date end) {
        return jdbcTemplate.queryForObject(SQL_RANGE_UNIQUE_USERS_NUMBER, new Object[]{begin, end}, Integer.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPermanentUsersNumber(Date begin, Date end) {
        return jdbcTemplate.queryForObject(SQL_PERMANENT_USERS_NUMBER, new Object[]{begin, end}, Integer.class);
    }
}
