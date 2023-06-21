package com.jpmc.theater;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocalDateProviderTests {

    private LocalDateProvider provider;

    @BeforeAll
    public void classSetup() {
        this.provider = LocalDateProvider.getInstance();
    }

    @Test
    public void testCurrentDate() {
        final LocalDate providerDate = provider.currentDate();
        final LocalDate dateNow = LocalDate.now();
        assertTrue(providerDate.isEqual(dateNow));
    }
}
