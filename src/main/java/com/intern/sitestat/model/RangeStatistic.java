package com.intern.sitestat.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class RangeStatistic {

    private int rangeVisitsNumber;
    private int rangeUniqueUsersNumber;
    private int permanentUsersNumber;

    @JsonCreator
    public RangeStatistic(int rangeVisitsNumber, int rangeUniqueUsersNumber, int permanentUsersNumber) {
        this.rangeVisitsNumber = rangeVisitsNumber;
        this.rangeUniqueUsersNumber = rangeUniqueUsersNumber;
        this.permanentUsersNumber = permanentUsersNumber;
    }

    public int getRangeVisitsNumber() {
        return rangeVisitsNumber;
    }

    public int getRangeUniqueUsersNumber() {
        return rangeUniqueUsersNumber;
    }

    public int getPermanentUsersNumber() {
        return permanentUsersNumber;
    }
}
