package com.dalehardware;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class TransactionTest {

    @Test
    void checkOut() {
        // The following tests correspond to the required tests within the design document.

        // As a note: This test (as well as the rest of the project) makes the assumption that the day-of renting
        // is included as a part of the number of days the tool is rented. So for example if a rental starts on 09/03
        // and is rented for 6 days it would be due on 09/09, not 09/10.

        RentalAgreement agreement;


        // Test 1.
        try {
            new Transaction("JAKR", "9/3/15", "5", "101").checkOut();
            assert false; // This should never happen.
        } catch (IllegalStateException e) {
            // Expecting an IllegalStateException as discountPercent is outside the allowable range.
            assert true;
        }

        // Test 2.
        agreement = new Transaction("LADW", "7/2/20", "3", "10").checkOut();
        assert agreement.getToolCode().equals("LADW");
        assert agreement.getToolType() == Inventory.ToolType.Ladder;
        assert agreement.getToolBrand() == Inventory.Brand.Werner;
        assert agreement.getRentalDays() == 3;
        assert agreement.getCheckOutDate().isEqual(LocalDate.of(2020, 7, 2));
        assert agreement.getDueDate().isEqual(LocalDate.of(2020, 7, 4));
        assert agreement.getChargeDays() == 2;
        assert agreement.getPreDiscountCharge() == 3.98;
        assert agreement.getDiscountPercent() == 10;
        assert agreement.getDiscountAmount() == 0.40;
        assert agreement.getFinalCharge() == 3.58;

        // Test 3.
        agreement = new Transaction("CHNS", "7/2/15", "5", "25").checkOut();
        assert agreement.getToolCode().equals("CHNS");
        assert agreement.getToolType() == Inventory.ToolType.Chainsaw;
        assert agreement.getToolBrand() == Inventory.Brand.Stihl;
        assert agreement.getRentalDays() == 5;
        assert agreement.getCheckOutDate().isEqual(LocalDate.of(2015, 7, 2));
        assert agreement.getDueDate().isEqual(LocalDate.of(2015, 7, 6));
        assert agreement.getChargeDays() == 3;
        assert agreement.getPreDiscountCharge() == 4.47;
        assert agreement.getDiscountPercent() == 25;
        assert agreement.getDiscountAmount() == 1.12;
        assert agreement.getFinalCharge() == 3.35;

        // Test 4.
        agreement = new Transaction("JAKD", "9/3/15", "6", "0").checkOut();
        assert agreement.getToolCode().equals("JAKD");
        assert agreement.getToolType() == Inventory.ToolType.Jackhammer;
        assert agreement.getToolBrand() == Inventory.Brand.DeWalt;
        assert agreement.getRentalDays() == 6;
        assert agreement.getCheckOutDate().isEqual(LocalDate.of(2015, 9, 3));
        assert agreement.getDueDate().isEqual(LocalDate.of(2015, 9, 8));
        assert agreement.getChargeDays() == 3;
        assert agreement.getPreDiscountCharge() == 8.97;
        assert agreement.getDiscountPercent() == 0;
        assert agreement.getDiscountAmount() == 0;
        assert agreement.getFinalCharge() == 8.97;

        // Test 5.
        agreement = new Transaction("JAKR", "7/2/15", "9", "0").checkOut();
        assert agreement.getToolCode().equals("JAKR");
        assert agreement.getToolType() == Inventory.ToolType.Jackhammer;
        assert agreement.getToolBrand() == Inventory.Brand.Ridgid;
        assert agreement.getRentalDays() == 9;
        assert agreement.getCheckOutDate().isEqual(LocalDate.of(2015, 7, 2));
        assert agreement.getDueDate().isEqual(LocalDate.of(2015, 7, 10));
        assert agreement.getChargeDays() == 6;
        assert agreement.getPreDiscountCharge() == 17.94;
        assert agreement.getDiscountPercent() == 0;
        assert agreement.getDiscountAmount() == 0;
        assert agreement.getFinalCharge() == 17.94;

        // Test 6.
        agreement = new Transaction("JAKR", "7/2/20", "4", "50").checkOut();
        assert agreement.getToolCode().equals("JAKR");
        assert agreement.getToolType() == Inventory.ToolType.Jackhammer;
        assert agreement.getToolBrand() == Inventory.Brand.Ridgid;
        assert agreement.getRentalDays() == 4;
        assert agreement.getCheckOutDate().isEqual(LocalDate.of(2020, 7, 2));
        assert agreement.getDueDate().isEqual(LocalDate.of(2020, 7, 5));
        assert agreement.getChargeDays() == 1;
        assert agreement.getPreDiscountCharge() == 2.99;
        assert agreement.getDiscountPercent() == 50;
        assert agreement.getDiscountAmount() == 1.50;   // Half of 2.99 is 1.495, where 5 would round up to 1.50.
        assert agreement.getFinalCharge() == 1.49;      // 2.99 - 1.50 is 1.49. That is why the discount amount does
                                                        // not equal the final amount.

        // Not a part of the mandatory test cases, but I figured this should be tested as well.
        // Negative rental days.
        try {
            new Transaction("JAKR", "7/2/20", "-1", "50").checkOut();
            assert false; // This should never happen.
        } catch (IllegalStateException e) {
            // Expecting an IllegalStateException as rentalDays is outside the allowable range.
            assert true;
        }
    }
}