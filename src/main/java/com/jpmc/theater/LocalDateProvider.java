package com.jpmc.theater;

import java.time.LocalDate;

public class LocalDateProvider {

    private static volatile LocalDateProvider instance = null;

    public static LocalDateProvider getInstance() {

        if (instance == null) {
            synchronized (LocalDateProvider.class) {

                if (instance == null) {
                    instance = new LocalDateProvider();
                }

            }
        }

        return instance;
    }

    /**
     * @return LocalDate
     */
    public LocalDate currentDate() {
            return LocalDate.now();
    }
}
