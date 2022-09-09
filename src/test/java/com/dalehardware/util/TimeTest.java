package com.dalehardware.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class TimeTest {

    @org.junit.jupiter.api.Test
    void containsHoliday() {
        // Only July and September contain a holiday.

        // January
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 1, 31);
        assert (!Time.containsHoliday(startDate, endDate));

        // February
        startDate = LocalDate.of(2022, 2, 1);
        endDate = LocalDate.of(2022, 2, 28);
        assert (!Time.containsHoliday(startDate, endDate));

        // March
        startDate = LocalDate.of(2022, 3, 1);
        endDate = LocalDate.of(2022, 3, 31);
        assert (!Time.containsHoliday(startDate, endDate));

        // April
        startDate = LocalDate.of(2022, 4, 1);
        endDate = LocalDate.of(2022, 4, 30);
        assert (!Time.containsHoliday(startDate, endDate));

        // May
        startDate = LocalDate.of(2022, 5, 1);
        endDate = LocalDate.of(2022, 5, 31);
        assert (!Time.containsHoliday(startDate, endDate));

        // June
        startDate = LocalDate.of(2022, 6, 1);
        endDate = LocalDate.of(2022, 6, 30);
        assert (!Time.containsHoliday(startDate, endDate));

        // July
        startDate = LocalDate.of(2022, 7, 1);
        endDate = LocalDate.of(2022, 7, 31);
        assert (Time.containsHoliday(startDate, endDate));

        // August
        startDate = LocalDate.of(2022, 8, 1);
        endDate = LocalDate.of(2022, 8, 31);
        assert (!Time.containsHoliday(startDate, endDate));

        // September
        startDate = LocalDate.of(2022, 9, 1);
        endDate = LocalDate.of(2022, 9, 30);
        assert (Time.containsHoliday(startDate, endDate));

        // October
        startDate = LocalDate.of(2022, 10, 1);
        endDate = LocalDate.of(2022, 10, 31);
        assert (!Time.containsHoliday(startDate, endDate));

        // November
        startDate = LocalDate.of(2022, 11, 1);
        endDate = LocalDate.of(2022, 11, 30);
        assert (!Time.containsHoliday(startDate, endDate));

        // December
        startDate = LocalDate.of(2022, 12, 1);
        endDate = LocalDate.of(2022, 12, 31);
        assert (!Time.containsHoliday(startDate, endDate));

        // Exactly the holiday date.
        startDate = LocalDate.of(2022, 7, 4);
        endDate = LocalDate.of(2022, 7, 4);
        assert (Time.containsHoliday(startDate, endDate));

        startDate = LocalDate.of(2022, 9, 5);
        endDate = LocalDate.of(2022, 9, 5);
        assert (Time.containsHoliday(startDate, endDate));

        // Out-of-order dates.
        startDate = LocalDate.of(2022, 12, 1);
        endDate = LocalDate.of(2022, 1, 1);
        try {
            assert (!Time.containsHoliday(startDate, endDate));
            assert false; // This should never happen.
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    @org.junit.jupiter.api.Test
    void isHoliday() {
        assert (Time.isHoliday(LocalDate.of(2022, 9, 5))); // Labor day 2022
        assert (!Time.isHoliday(LocalDate.of(2022, 9, 4)));
        assert (!Time.isHoliday(LocalDate.of(2022, 9, 6)));
        assert (Time.isHoliday(LocalDate.of(2023, 9, 4))); // Labor day 2023
        assert (!Time.isHoliday(LocalDate.of(2021, 9, 5)));
        assert (!Time.isHoliday(LocalDate.of(2022, 10, 5)));
        assert (!Time.isHoliday(LocalDate.of(2022, 10, 7)));
        assert (Time.isHoliday(LocalDate.of(2022, 7, 4)));
        assert (Time.isHoliday(LocalDate.of(2021, 7, 5))); // Observed on Monday.
        assert (!Time.isHoliday(LocalDate.of(2021, 7, 4)));
        assert (Time.isHoliday(LocalDate.of(2020, 7, 3))); // Observed on Friday
        assert (!Time.isHoliday(LocalDate.of(2020, 7, 4)));
    }

    @org.junit.jupiter.api.Test
    void isLaborDay() {
        assert (Time.isLaborDay(LocalDate.of(2022, 9, 5))); // Labor day 2022
        assert (!Time.isLaborDay(LocalDate.of(2022, 9, 4)));
        assert (!Time.isLaborDay(LocalDate.of(2022, 9, 6)));
        assert (Time.isLaborDay(LocalDate.of(2023, 9, 4))); // Labor day 2023
        assert (!Time.isLaborDay(LocalDate.of(2021, 9, 5)));
        assert (!Time.isLaborDay(LocalDate.of(2022, 10, 5)));
        assert (!Time.isLaborDay(LocalDate.of(2022, 10, 7)));
    }

    @org.junit.jupiter.api.Test
    void isIndependenceDay() {
        assert (Time.isIndependenceDay(LocalDate.of(2022, 7, 4)));
        assert (Time.isIndependenceDay(LocalDate.of(2021, 7, 5))); // Observed on Monday.
        assert (!Time.isIndependenceDay(LocalDate.of(2021, 7, 4)));
        assert (Time.isIndependenceDay(LocalDate.of(2020, 7, 3))); // Observed on Friday
        assert (!Time.isIndependenceDay(LocalDate.of(2020, 7, 4)));
    }

    @Test
    void numberOfWeekendsInRange() {
        LocalDate startDate = LocalDate.of(2022, 9, 9);
        LocalDate endDate = LocalDate.of(2022, 9, 10);
        assert (Time.numberOfWeekendDaysInRange(startDate, endDate) == 1);

        startDate = LocalDate.of(2022, 9, 1);
        endDate = LocalDate.of(2022, 9, 30);
        assert (Time.numberOfWeekendDaysInRange(startDate, endDate) == 8);

        startDate = LocalDate.of(2022, 9, 12);
        endDate = LocalDate.of(2022, 9, 16);
        assert (Time.numberOfWeekendDaysInRange(startDate, endDate) == 0);

        startDate = LocalDate.of(2022, 9, 10);
        endDate = LocalDate.of(2022, 9, 11);
        assert (Time.numberOfWeekendDaysInRange(startDate, endDate) == 2);
    }
}