package com.dalehardware.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

/**
 * Utility class to manage any time related functionalities, specifically related to calculating cost of a rental.
 */
public class Time {
    /**
     * Determines if a holiday is within the specified range. All date ranges are inclusive.
     * @param startDate The beginning of the date-range.
     * @param endDate The end of the date-range.
     * @return Returns true if at least one holiday is within the range, otherwise returns false.
     */
    public static boolean containsHoliday(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException();
        }

        for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
            if (isHoliday(date)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Determines the number of days within a range (between startDate and endDate) that are "Holidays"
     * (in our case that is just Labor Day, Independence Day, and their contingencies). All date ranges are inclusive.
     * @param startDate The beginning of the date-range.
     * @param endDate The end of the date-range.
     * @return The number of holidays in the range as an int.
     */
    public static int numberOfHolidaysInRange(LocalDate startDate, LocalDate endDate) {
        int numberOfHolidaysInRange = 0;

        for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
            if (Time.isLaborDay(date) || Time.isIndependenceDay(date)) {
                numberOfHolidaysInRange++;
            }
        }

        return numberOfHolidaysInRange;
    }

    /**
     * Determines if a specific date falls on either Labor Day or Independence Day.
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
        // If July 4 is on a Saturday we count the previous friday as Independence Day.
        if (independenceDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
            independenceDay = independenceDay.minusDays(1);
        }
        // If July 4 is on a Sunday we count the following Monday as Independence Day.
        if (independenceDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            independenceDay = independenceDay.plusDays(1);
        }

        return date.isEqual(independenceDay);
    }

    /**
     * Returns the number of weekend days between two LocalDates. All date ranges are inclusive.
     * @param startDate Starting date of the range.
     * @param endDate Ending date of the range.
     * @return Returns the total number of weekend days within the range as an integer.
     */
    public static int numberOfWeekendDaysInRange(LocalDate startDate, LocalDate endDate) {
        int numberOfWeekendDaysInRange = 0;

        for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                numberOfWeekendDaysInRange++;
            }
        }

        return numberOfWeekendDaysInRange;
    }

    /**
     * Returns the number of week days two LocalDates.
     * Technically not necessary based on the provided test cases, however
     * the tool policy description it implies there could exist a tool that would
     * be free to rent on a week day. All date ranges are inclusive.
     * @param startDate Starting date of the range.
     * @param endDate Ending date of the range.
     * @return Returns the total number of week days within the range as an integer.
     */
    public static int numberOfWeekDaysInRange(LocalDate startDate, LocalDate endDate) {
        int numberOfWeekDaysInRange = 0;

        for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
            if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                numberOfWeekDaysInRange++;
            }
        }

        return numberOfWeekDaysInRange;
    }

}
