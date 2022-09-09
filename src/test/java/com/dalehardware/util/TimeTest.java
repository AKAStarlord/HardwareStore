package com.dalehardware.util;

import java.time.LocalDate;

class TimeTest {

    @org.junit.jupiter.api.Test
    void containsHoliday() {
        // Only July and September contain a holiday.

        // January
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 1, 31);
        assert (Time.containsHoliday(startDate, endDate) == false);

        // February
        startDate = LocalDate.of(2022, 2, 1);
        endDate = LocalDate.of(2022, 2, 28);
        assert (Time.containsHoliday(startDate, endDate) == false);

        // March
        startDate = LocalDate.of(2022, 3, 1);
        endDate = LocalDate.of(2022, 3, 31);
        assert (Time.containsHoliday(startDate, endDate) == false);

        // April
        startDate = LocalDate.of(2022, 4, 1);
        endDate = LocalDate.of(2022, 4, 30);
        assert (Time.containsHoliday(startDate, endDate) == false);

        // May
        startDate = LocalDate.of(2022, 5, 1);
        endDate = LocalDate.of(2022, 5, 31);
        assert (Time.containsHoliday(startDate, endDate) == false);

        // June
        startDate = LocalDate.of(2022, 6, 1);
        endDate = LocalDate.of(2022, 6, 30);
        assert (Time.containsHoliday(startDate, endDate) == false);

        // July
        startDate = LocalDate.of(2022, 7, 1);
        endDate = LocalDate.of(2022, 7, 31);
        assert (Time.containsHoliday(startDate, endDate) == true);

        // August
        startDate = LocalDate.of(2022, 8, 1);
        endDate = LocalDate.of(2022, 8, 31);
        assert (Time.containsHoliday(startDate, endDate) == false);

        // September
        startDate = LocalDate.of(2022, 9, 1);
        endDate = LocalDate.of(2022, 9, 30);
        assert (Time.containsHoliday(startDate, endDate) == true);

        // October
        startDate = LocalDate.of(2022, 10, 1);
        endDate = LocalDate.of(2022, 10, 31);
        assert (Time.containsHoliday(startDate, endDate) == false);

        // November
        startDate = LocalDate.of(2022, 11, 1);
        endDate = LocalDate.of(2022, 11, 30);
        assert (Time.containsHoliday(startDate, endDate) == false);

        // December
        startDate = LocalDate.of(2022, 12, 1);
        endDate = LocalDate.of(2022, 12, 31);
        assert (Time.containsHoliday(startDate, endDate) == false);

        // Out-of-order dates
        startDate = LocalDate.of(2022, 12, 1);
        endDate = LocalDate.of(2022, 1, 1);
        try {
            assert (Time.containsHoliday(startDate, endDate) == false);
            assert false; // This should never happen.
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    @org.junit.jupiter.api.Test
    void isHoliday() {
        assert (Time.isHoliday(LocalDate.of(2022, 9, 5)) == true); // Labor day 2022
        assert (Time.isHoliday(LocalDate.of(2022, 9, 4)) == false);
        assert (Time.isHoliday(LocalDate.of(2022, 9, 6)) == false);
        assert (Time.isHoliday(LocalDate.of(2023, 9, 4)) == true); // Labor day 2023
        assert (Time.isHoliday(LocalDate.of(2021, 9, 5)) == false);
        assert (Time.isHoliday(LocalDate.of(2022, 10, 5)) == false);
        assert (Time.isHoliday(LocalDate.of(2022, 10, 7)) == false);
        assert (Time.isHoliday(LocalDate.of(2022, 07, 04)) == true);
        assert (Time.isHoliday(LocalDate.of(2021, 07, 05)) == true); // Observed on Monday.
        assert (Time.isHoliday(LocalDate.of(2021, 07, 04)) == false);
        assert (Time.isHoliday(LocalDate.of(2020, 07, 03)) == true); // Observed on Friday
        assert (Time.isHoliday(LocalDate.of(2020, 07, 04)) == false);
    }

    @org.junit.jupiter.api.Test
    void isLaborDay() {
        assert (Time.isLaborDay(LocalDate.of(2022, 9, 5)) == true); // Labor day 2022
        assert (Time.isLaborDay(LocalDate.of(2022, 9, 4)) == false);
        assert (Time.isLaborDay(LocalDate.of(2022, 9, 6)) == false);
        assert (Time.isLaborDay(LocalDate.of(2023, 9, 4)) == true); // Labor day 2023
        assert (Time.isLaborDay(LocalDate.of(2021, 9, 5)) == false);
        assert (Time.isLaborDay(LocalDate.of(2022, 10, 5)) == false);
        assert (Time.isLaborDay(LocalDate.of(2022, 10, 7)) == false);
    }

    @org.junit.jupiter.api.Test
    void isIndependenceDay() {
        assert (Time.isIndependenceDay(LocalDate.of(2022, 07, 04)) == true);
        assert (Time.isIndependenceDay(LocalDate.of(2021, 07, 05)) == true); // Observed on Monday.
        assert (Time.isIndependenceDay(LocalDate.of(2021, 07, 04)) == false);
        assert (Time.isIndependenceDay(LocalDate.of(2020, 07, 03)) == true); // Observed on Friday
        assert (Time.isIndependenceDay(LocalDate.of(2020, 07, 04)) == false);
    }
}