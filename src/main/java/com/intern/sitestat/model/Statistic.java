package com.intern.sitestat.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Statistic {

    private int todaysVisitsNumber;
    private int todaysUniqueUsersNumber;

    @JsonCreator
    public Statistic(int todaysVisitsNumber, int todaysUniqueUsersNumber) {
        this.todaysVisitsNumber = todaysVisitsNumber;
        this.todaysUniqueUsersNumber = todaysUniqueUsersNumber;
    }

    public int getTodaysVisitsNumber() {
        return todaysVisitsNumber;
    }

    public int getTodaysUniqueUsersNumber() {
        return todaysUniqueUsersNumber;
    }
}
