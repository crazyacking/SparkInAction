package com.hut.demo.spark;

/**
 * Created by crazyacking on 2017/1/1.
 */
public enum TimeUnit {
    MINUTES(12),
    HOURS(10),
    DAYS(5),
    MONTHS(2);

    private int calendarUnit;

    TimeUnit(int calendarUnit) {
        this.calendarUnit = calendarUnit;
    }

    public int getCalendarUnit() {
        return this.calendarUnit;
    }
}
