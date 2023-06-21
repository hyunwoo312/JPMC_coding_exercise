package com.jpmc.theater;

import com.google.gson.Gson;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Theater {

    private final Gson gson;
    private final LocalDateProvider provider;
    private final List<Showing> schedule;

    /**
     * @param provider singleton LocalDate provider
     */
    public Theater(final LocalDateProvider provider) {
        this.gson = new Gson();
        this.provider = provider;

        final Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        final Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        final Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        schedule = List.of(
            new Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
            new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
            new Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
            new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
            new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
            new Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
            new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
            new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
            new Showing(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0)))
        );
    }

    /**
     * @param customer Customer object
     * @param sequence movie showing sequence for the day
     * @param totalTicketscount total number of tickets for reservation
     * @return Reservation
     */
    public Reservation reserve(final Customer customer, final int sequence, final int totalTicketsCount) {
        Showing showing;
        try {
            showing = schedule.get(sequence - 1);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, showing, totalTicketsCount);
    }

    public void printSchedule() {
        System.out.println(provider.currentDate());
        System.out.println("===================================================");
        schedule.forEach(s ->
                System.out.println(s.getSequenceOfTheDay() + ": " + s.getStartDateTime() + " " + s.getMovie().getTitle() + " " + humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovieFee())
        );
        System.out.println("===================================================");
    }

    public void printScheduleJSON() {
        final String jsonOutput = gson.toJson(schedule);
        System.out.println(jsonOutput);
    } 

    private String humanReadableFormat(Duration duration) {
        final String readableTimeFormat = "(%d hour%s %d minute%s)";
        final long hour = duration.toHours();
        final long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(hour);

        return String.format(readableTimeFormat, hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    // (s) postfix should be added to handle plural correctly
    private String handlePlural(long value) {
        if (value == 1) {
            return "";
        }
        else {
            return "s";
        }
    }

    public static void main(String[] args) {
        Theater theater = new Theater(LocalDateProvider.getInstance());
        theater.printSchedule();
    }
}
