package com.jpmc.theater;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationTests {

    private LocalDateProvider provider;

    private Customer customerA;
    private Showing showingA;

    @BeforeAll
    public void classSetup() {
        this.provider = LocalDateProvider.getInstance();

        this.customerA = new Customer("John Doe");
        this.showingA = new Showing(
            new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10.0, 1),
            1,
            LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 0))
        );
    }

    @Test
    public void testTotalFee() {
        final int ticketCountA = 5;
        final Reservation testReservationA = new Reservation(customerA, showingA, ticketCountA);
        assertEquals(showingA.getMovieFee() * ticketCountA, testReservationA.getTotalFee());
    }
}
