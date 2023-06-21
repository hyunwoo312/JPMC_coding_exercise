package com.jpmc.theater;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowingTests {

    private LocalDateProvider provider;

    @BeforeAll
    public void classSetup() {
        this.provider = LocalDateProvider.getInstance();
    }

    @Test
    public void testMovieFeeFirstSequenceDiscount() {
        final Showing showingA = new Showing(
            new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10.0, 1),
            1,
            LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 0))
        );
        assertEquals(showingA.getMovieFee(), 7);
    }

    @Test
    public void testMovieFeeSeventhDayOfMonthDiscount() {
        final Showing showingB = new Showing(
            new Movie("Turning Red", Duration.ofMinutes(85), 9, 0),
            4,
            LocalDateTime.of(LocalDate.of(2023, 6, 7), LocalTime.of(14, 0))
        );
        assertEquals(showingB.getMovieFee(), 8);
    }
}
