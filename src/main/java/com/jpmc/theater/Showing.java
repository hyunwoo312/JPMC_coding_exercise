package com.jpmc.theater;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Showing {
    private static final int MOVIE_CODE_SPECIAL = 1;
    private static final LocalTime DISCOUNT_START_TIME = LocalTime.of(11, 0);
    private static final LocalTime DISCOUNT_END_TIME = LocalTime.of(16, 0);

    private final Movie movie;
    private final int sequenceOfTheDay;
    private final LocalDateTime showStartDateTime;
    private final double movieFee;

    /**
     * @param movie Movie object
     * @param sequenceOfTheDay movie showing sequence for the day
     * @param showStartTime movie start time
     */
    public Showing(final Movie movie, final int sequenceOfTheDay, final LocalDateTime showStartDateTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartDateTime = showStartDateTime;

        this.movieFee = this.movie.getTicketPrice() - this.getDiscount();
    }

    /**
     * @return Movie
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * @return int
     */
    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    /**
     * @return LocalDateTime
     */
    public LocalDateTime getStartDateTime() {
        return showStartDateTime;
    }

    /**
     * @return double
     */
    public double getMovieFee() {
        return movieFee;
    }

    /**
     * @param sequence movie showing sequence for the day
     * @return boolean
     */
    public boolean isSequence(final int sequence) {
        return this.sequenceOfTheDay == sequence;
    }

    /**
     * @return double
     */
    private double getDiscount() {
        final LocalTime showStartTime = showStartDateTime.toLocalTime();
        double percentageDiscountAmount = 0;

        if (showStartTime.isAfter(DISCOUNT_START_TIME) && showStartTime.isBefore(DISCOUNT_END_TIME)) {
            percentageDiscountAmount = movie.getTicketPrice() * 0.25;
        } else if (movie.getSpecialCode() == MOVIE_CODE_SPECIAL) {
            percentageDiscountAmount = movie.getTicketPrice() * 0.2;
        }

        double flatDiscountAmount = 0;

        if (sequenceOfTheDay == 1) {
            flatDiscountAmount = 3;
        } else if (sequenceOfTheDay == 2) {
            flatDiscountAmount = 2;
        } else if (showStartDateTime.getDayOfMonth() == 7) {
            flatDiscountAmount = 1;
        }

        return Math.max(percentageDiscountAmount, flatDiscountAmount);
    }
}
