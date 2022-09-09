package com.dalehardware;

import com.dalehardware.util.Time;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

/**
 * The transaction class holds the state given by the user and processes (via checkOut()) the transaction.
 */
public class Transaction {

    private String toolCode;
    private LocalDate checkoutDate;
    private int rentalDays;
    private double discountPercent;

    public Transaction(String toolCode, String checkoutDate, String rentalDays, String discountPercent)
            throws DateTimeParseException {
        this.toolCode = toolCode;
        this.checkoutDate = LocalDate.from(
                DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).parse(checkoutDate));
        this.rentalDays = Integer.parseInt(rentalDays);
        this.discountPercent = Double.parseDouble(discountPercent);
    }

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    /**
     * Processes the user's request by calculating a RentalAgreement from the current
     * state of this Transaction object.
     * An assumption is made here that the day-of renting counts as one of the rental days.
     * @return A completed RentalAgreement object, ready to be displayed to the end user.
     */
    public RentalAgreement checkOut() {

        // Normally I'd put these in Store.java, or wherever input would come from as IllegalArgumentExceptions,
        // but per the design document these exceptions must at least be handled at "Check Out".
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalStateException("Expected a percent between 0-100, instead received " + discountPercent);
        }

        if (rentalDays < 1) {
            throw new IllegalStateException("Expected number of rental days to be one or greater, instead received "
                    + rentalDays);
        }

        Tool tool = Inventory.list.get(toolCode);
        ToolPolicy policy = Inventory.policy.get(tool.getType());

        LocalDate dueDate = checkoutDate.plusDays(rentalDays - 1); // Minus one to include day-of.
        if (dueDate.isBefore(checkoutDate)) {
            // This shouldn't really ever happen, but just for completeness.
            throw new IllegalStateException(
                    "Due Date (" + dueDate + ") cannot be before Checkout Date (" + checkoutDate + ").");
        }

        // Charge days is rental days minus relevant weekends and holidays, based on tool policy.
        int chargeDays = rentalDays;
        if (!policy.isWeekendCharge()) {
            chargeDays -= Time.numberOfWeekendDaysInRange(checkoutDate, dueDate);
        }
        if (!policy.isHolidayCharge()) {
            chargeDays -= Time.numberOfHolidaysInRange(checkoutDate, dueDate);
        }
        if (!policy.isWeekdayCharge()) {
            chargeDays -= Time.numberOfWeekDaysInRange(checkoutDate, dueDate);
        }

        // Round all decimals to two digits for currency.
        double preDiscountCharge = chargeDays * policy.getDailyCharge();
        double discountAmount = round(preDiscountCharge * (discountPercent / 100));
        double finalCharge = round(preDiscountCharge - discountAmount);

        return new RentalAgreement(
                tool.getCode(),
                tool.getType(),
                tool.getBrand(),
                rentalDays,
                checkoutDate,
                dueDate,
                chargeDays,
                preDiscountCharge,
                discountPercent,
                discountAmount,
                finalCharge);
    }

    /**
     * Helper function to ensure that everything is rounded properly when dealing with currencies. RoundingMode.HALF_UP
     * is the most similar to what people usually use (rounding 5+ up, and everything else down).
     * @param unrounded The initial un-rounded value.
     * @return The new value rounded to the nearest two decimal places.
     */
    private double round(double unrounded) {
        BigDecimal bd = new BigDecimal(unrounded);
        BigDecimal rounded = bd.setScale(2, RoundingMode.HALF_UP);
        return rounded.doubleValue();
    }
}
