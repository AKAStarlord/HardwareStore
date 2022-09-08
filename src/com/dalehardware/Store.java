package com.dalehardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeParseException;

public class Store {

    public static void main(String []args) {
        String input = "";

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Until specifically requested to exit, keep accepting input and printing appropriate
        // Rental Agreements.
        while (!input.equals("EXIT")) {
            try {
                System.out.println("Tool Code: ");
                String toolCode = input = reader.readLine();
                System.out.println("Checkout Date: ");
                String checkoutDate = reader.readLine();
                System.out.println("Number of Rental Days: ");
                String rentalDays = reader.readLine();
                System.out.println("Discount (0-100): ");
                String discount = reader.readLine();

                // Process user input.
                Transaction transaction = new Transaction(toolCode, checkoutDate, rentalDays, discount);
                transaction.process();

            } catch (IOException e) {
                // If there is an exception for some reason here, not much really
                // needs to happen as the terminal will keep looping for new input
                // until the user explicitly enters in 'EXIT'.
                System.out.println("Bad input!");
                e.printStackTrace();
            } catch (DateTimeParseException e) {
                System.out.println("Bad Checkout Date entered!");
            }
        }
    }
}
