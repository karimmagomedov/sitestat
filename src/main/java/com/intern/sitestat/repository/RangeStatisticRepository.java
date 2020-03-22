package com.intern.sitestat.repository;

import java.util.Date;

/**
 * The interface provides opportunity for working with events' data in the specific range.
 */
public interface RangeStatisticRepository {

    /**
     * Method returns the number of visits in the specific range.
     *
     * @param begin the start date of the range
     * @param end   the end date of the range
     * @return a {@code int} number of visits in the range
     */
    int getRangeVisitsNumber(Date begin, Date end);

    /**
     * Method returns the number of unique users in the specific range.
     *
     * @param begin the start date of the range
     * @param end   the end date of the range
     * @return a {@code int} number of unique users in the range
     */
    int getRangeUniqueUsersNumber(Date begin, Date end);

    /**
     * Method returns the number of permanent users in the specific range.
     *
     * @param begin the start date of the range
     * @param end   the end date of the range
     * @return a {@code int} number of users who has seen more then 10 pages in the range
     */
    int getPermanentUsersNumber(Date begin, Date end);

}
