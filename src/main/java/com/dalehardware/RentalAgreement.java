package com.dalehardware;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * RentalAgreement holds and formats all the data relevant to completing a transaction with a customer.
 */
public class RentalAgreement {

    private String toolCode;
    private Inventory.ToolType toolType;
    private Inventory.Brand toolBrand;
    private int rentalDays;
    private LocalDate checkOutDate;
    private LocalDate dueDate;
    private int chargeDays;
    private double preDiscountCharge;
    private double discountPercent;
    private double discountAmount;
    private double finalCharge;


    public RentalAgreement(String toolCode, Inventory.ToolType toolType, Inventory.Brand toolBrand, int rentalDays,
                           LocalDate checkOutDate, LocalDate dueDate, int chargeDays, double preDiscountCharge,
                           double discountPercent, double discountAmount, double finalCharge) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.toolBrand = toolBrand;
        this.rentalDays = rentalDays;
        this.checkOutDate = checkOutDate;
        this.dueDate = dueDate;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public Inventory.ToolType getToolType() {
        return toolType;
    }

    public void setToolType(Inventory.ToolType toolType) {
        this.toolType = toolType;
    }

    public Inventory.Brand getToolBrand() {
        return toolBrand;
    }

    public void setToolBrand(Inventory.Brand toolBrand) {
        this.toolBrand = toolBrand;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public void setChargeDays(int chargeDays) {
        this.chargeDays = chargeDays;
    }

    public double getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public void setPreDiscountCharge(double preDiscountCharge) {
        this.preDiscountCharge = preDiscountCharge;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getFinalCharge() {
        return finalCharge;
    }

    public void setFinalCharge(double finalCharge) {
        this.finalCharge = finalCharge;
    }

    /**
     * Overriding the default toString to print as described
     * in the design document.
     * @return Returns a nicely formatted string for printing.
     */
    @Override
    public String toString() {
        return  "Rental Agreement: " +
                "\nTool Code: " + toolCode +
                "\nTool Type: " + toolType +
                "\nTool Brand: " + toolBrand +
                "\nRental Days: " + rentalDays +
                "\nCheck Out Date: "  + checkOutDate.format(DateTimeFormatter.ofPattern("MM/dd/yy")) +
                "\nDue Date: " + dueDate.format(DateTimeFormatter.ofPattern("MM/dd/yy")) +
                "\nCharge Days: " + chargeDays +
                "\nPre-discount Charge: " + NumberFormat.getCurrencyInstance().format(preDiscountCharge) +
                "\nDiscount Percent: " + String.format("%.0f%%", discountPercent) +
                "\nDiscount Amount: " + NumberFormat.getCurrencyInstance().format(discountAmount) +
                "\nFinal Charge: " + NumberFormat.getCurrencyInstance().format(finalCharge) +
                "\n\n\n\n";
    }
}
