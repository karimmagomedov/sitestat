package com.intern.sitestat.repository;

/**
 * The interface provides opportunity for working with events' data.
 */
public interface StatisticRepository {

    /**
     * Method for creating event.
     *
     * @param userId id of the user
     * @param pageId id of the page
     */
    void addEvent(String userId, String pageId);

    /**
     * Method returns today's number of visits
     */
    int getTodaysVisitsNumber();

    /**
     * Method returns today's number of unique users
     */
    int getTodaysUniqueUsersNumber();
}
