package com.dalehardware;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Transaction {

    private String toolCode;
    private LocalDate checkoutDate;
    private int rentalDays;
    private double discount;

    public Transaction(String toolCode, String checkoutDate, String rentalDays, String discount) throws DateTimeParseException {
        this.toolCode = toolCode;
        this.checkoutDate = LocalDate.parse(checkoutDate);
        this.rentalDays = Integer.parseInt(rentalDays);
        this.discount = Double.parseDouble(discount);
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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    // Processes the users request by ... TODO
    public void process() {

    }
}
