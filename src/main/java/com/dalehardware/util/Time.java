package com.dalehardware.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

public class Time {
    public static boolean containsHoliday(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException();
        }

        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            if (isHoliday(date)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Determines if a specific date falls on either Labor Day or Independence Day.
     *
     * @param date The date being checked against.
     * @return True if date is on a holiday, otherwise returns false.
     */
    public static boolean isHoliday(LocalDate date) {
        return isIndependenceDay(date) || isLaborDay(date);
    }

    /**
     * Determines if a specific date falls on Labor Day, the first Monday of September.
     *
     * @param date The date being checked against.
     * @return returns true if date is on Labor Day, otherwise returns false.
     */
    public static boolean isLaborDay(LocalDate date) {
        // Labor Day is the first Monday in September.
        LocalDate laborDay = LocalDate.of(
                date.getYear(), 9, 1).with(firstInMonth(DayOfWeek.MONDAY));

        return (date.getMonth() == laborDay.getMonth() && date.getDayOfMonth() == laborDay.getDayOfMonth());
    }

    /**
     * Determines if a specific date falls on Independence Day, July 4th.
     * If July 4th is a Saturday it is celebrated the previous Friday. If July 4th falls on a Sunday it is celebrated
     * on the following Monday.
     *
     * @param date The date being checked against.
     * @return returns true if date is on Independence Day, otherwise returns false.
     */
    public static boolean isIndependenceDay(LocalDate date) {
        LocalDate independenceDay = LocalDate.of(date.getYear(), 7, 4);
        if (independenceDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
            independenceDay = independenceDay.minusDays(1);
        }
        if (independenceDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            independenceDay = independenceDay.plusDays(1);
        }

        return date.isEqual(independenceDay);
    }

}
