package com.jpmc.theater;

import java.time.Duration;
import java.util.Objects;

public class Movie {

    private final String title;
    private final Duration runningTime;
    private final double ticketPrice;
    private final int specialCode;

    /**
     * @param title movie title
     * @param runningTime movie duration
     * @param ticketPrice movie ticket price
     * @param specialCode movie special code
     */
    public Movie(final String title, final Duration runningTime, final double ticketPrice, final int specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    /**
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return Duration
     */
    public Duration getRunningTime() {
        return runningTime;
    }

    /**
     * @return double
     */
    public double getTicketPrice() {
        return ticketPrice;
    }

    /**
     * @return double
     */
    public double getSpecialCode() {
        return specialCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, runningTime, ticketPrice, specialCode);
    }
}