package com.jpmc.theater;

import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TheaterTests {

    private Customer customerA;
    private Theater theaterA;
    private ByteArrayOutputStream outContent;

    @BeforeAll
    public void classSetup() {
        this.customerA = new Customer("John Doe");
        this.theaterA = new Theater(LocalDateProvider.getInstance());
        this.outContent = new ByteArrayOutputStream();
    }

    @Test
    public void testReserveCorrectSequence() {
        final Reservation reservation = theaterA.reserve(customerA, 1, 4);

        assertEquals(reservation.getTotalFee(), 36);
    }

    @Test
    public void testReserveWrongSequenceThrowsIllegalStateException() {
        assertThrows(IllegalStateException.class,
            () -> theaterA.reserve(customerA, 0, 5));
    }

    @Test
    public void testPrintScheduleInText() {
        theaterA.printSchedule();
    }

    @Test
    public void testPrintScheduleInJSON() {
        theaterA.printScheduleJSON();
    }
}
