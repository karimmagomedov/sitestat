package com.intern.sitestat.repository;

import java.util.Date;

/**
 * The interface provides opportunity for working with events' data in the specific range.
 */
public interface RangeStatisticRepository {

    int getRangeVisitsNumber(Date begin, Date end);

    int getRangeUniqueUsersNumber(Date begin, Date end);

    int getPermanentUsersNumber(Date begin, Date end);

}
