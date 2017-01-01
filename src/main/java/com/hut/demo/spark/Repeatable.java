package com.hut.demo.spark;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by crazyacking on 2017/1/1.
 */
public class Repeatable {
    public static final TimeZone UTC = TimeZone.getTimeZone("UTC");
    private String name;
    private Date baseline;
    private TimeZone timeZone;
    private int frequency;
    private TimeUnit timeUnit;

    public Repeatable() {
    }

    int getOccurrence(Date nominalTime, Date timeLimit) {
        int occurrence = -1;
        long positiveDiff = nominalTime.getTime() - this.getBaseline().getTime();
        if (positiveDiff >= 0L) {
            Calendar calendar = Calendar.getInstance(this.getTimeZone());
            calendar.setLenient(true);
            calendar.setTime(this.getBaseline());

            for (occurrence = 0; calendar.getTime().compareTo(nominalTime) < 0; ++occurrence) {
                if (timeLimit != null && calendar.getTime().compareTo(timeLimit) > 0) {
                    return -1;
                }

                calendar.add(this.getTimeUnit().getCalendarUnit(), this.getFrequency());
            }

            long nominalCurrentDelta = nominalTime.getTime() - calendar.getTime().getTime();
            positiveDiff = calendar.getTime().getTime() - this.getBaseline().getTime() + nominalCurrentDelta;
            if (positiveDiff < 0L) {
                occurrence = -1;
            }
        }

        return occurrence;
    }

    public int getOccurrence(Date nominalTime) {
        return this.getOccurrence(nominalTime, null);
    }

    Date getOccurrenceTime(Date nominalTime, int occurrenceOffset, Date timeLimit) {
        Date date = null;
        int occurrence = this.getOccurrence(nominalTime, timeLimit);
        if (occurrence > -1) {
            occurrence += occurrenceOffset;
            occurrence = occurrence >= 0 ? occurrence : -1;
        }

        if (occurrence > -1) {
            Calendar calendar = Calendar.getInstance(this.getTimeZone());
            calendar.setLenient(true);
            calendar.setTime(this.getBaseline());
            calendar.add(this.getTimeUnit().getCalendarUnit(), this.getFrequency() * occurrence);
            date = calendar.getTime();
        }

        return date;
    }

    public Date getOccurrenceTime(Date nominalTime, int occurrenceOffset) {
        return this.getOccurrenceTime(nominalTime, occurrenceOffset, null);
    }

    public Date getTime(int occurrence) {
        if (occurrence < 0) {
            throw new IllegalArgumentException("occurrence cannot be <0");
        } else {
            Calendar calendar = Calendar.getInstance(this.getTimeZone());
            calendar.setLenient(true);
            calendar.setTime(this.getBaseline());
            calendar.add(this.getTimeUnit().getCalendarUnit(), this.getFrequency() * occurrence);
            return calendar.getTime();
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBaseline() {
        return this.baseline;
    }

    public void setBaseline(Date baseline) {
        this.baseline = baseline;
    }

    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public TimeUnit getTimeUnit() {
        return this.timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }
}
