package com.jpmc.theater;

import java.time.LocalTime;

public class Reservation {
    private final Customer customer;
    private final Showing showing;
    private final int audienceCount;
    private final double totalFee;

    /**
     * @param customer Customer object
     * @param showing Showing object
     * @param audienceCount total number of audience
     */
    public Reservation(final Customer customer, final Showing showing, final int audienceCount) {
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;

        this.totalFee = this.showing.getMovieFee() * audienceCount;
    }

    /**
     * @return double
     */
    public double getTotalFee() {
        return totalFee;
    }
}