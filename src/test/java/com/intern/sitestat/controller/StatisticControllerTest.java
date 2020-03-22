package com.intern.sitestat.controller;

import com.intern.sitestat.model.RangeStatistic;
import com.intern.sitestat.model.Statistic;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class StatisticControllerTest {

    private static final String SQL_FILL_DB = "INSERT INTO events VALUES (?, 'testUserId', 'testPageId', '2020-03-22')";
    private static final String URL_PATH = "/statistic";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    static final int PORT = 8080;
    private static final String SQL_CLEANUP_DATA_CASCADE = "TRUNCATE events CASCADE;";

    @AfterEach
    void dispose() {
        jdbcTemplate.execute(SQL_CLEANUP_DATA_CASCADE);
    }

    @Test
    void createEventAndGetStat() {
        HashMap<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("userId", "userId1");
        parametersMap.put("pageId", "pageId1");

        Statistic statistic = given()
            .queryParams(parametersMap)
            .when()
            .post(URL_PATH)
            .then()
            .statusCode(HttpStatus.CREATED.value())
            .extract()
            .as(Statistic.class);

        assertEquals(1, statistic.getTodaysUniqueUsersNumber());
        assertEquals(1, statistic.getTodaysVisitsNumber());
    }

    @Test
    void getRangeStat() {
        prepareDatabase();
        HashMap<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("begin", "2020-03-20");
        parametersMap.put("end", "2020-03-23");

        RangeStatistic rangeStatistic = given()
            .queryParams(parametersMap)
            .when()
            .get(URL_PATH)
            .then()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .as(RangeStatistic.class);

        assertEquals(20, rangeStatistic.getRangeVisitsNumber());
        assertEquals(1, rangeStatistic.getRangeUniqueUsersNumber());
        assertEquals(1, rangeStatistic.getPermanentUsersNumber());
    }

    @Test
    void getRangeStatWrongArguments() {
        HashMap<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("begin", "null");
        parametersMap.put("end", "null");

        given().queryParams(parametersMap).when().get(URL_PATH).then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void getRangeStatWrongDates() {
        HashMap<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("begin", "2020-03-20");
        parametersMap.put("end", "2020-03-19");

        given().queryParams(parametersMap).when().get(URL_PATH).then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    private void prepareDatabase() {
        for (int i = 0; i < 20; i++) {
            jdbcTemplate.update(SQL_FILL_DB, UUID.randomUUID());
        }
    }

    private RequestSpecification given() {
        return RestAssured.given()
            .port(PORT);
    }
}
