package PracticePrograms;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class LocalDateTimeApi {
    public static void main(String[] args) {
        //Local Date
        LocalDate localDate = LocalDate.now();
        LocalDate.of(2015, 02, 20);
        LocalDate.parse("2015-02-20");
        int twelve = LocalDate.parse("2016-06-12").getDayOfMonth();
        LocalDate previousMonthSameDay = LocalDate.now().minus(1, ChronoUnit.MONTHS);
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        boolean leapYear = LocalDate.now().isLeapYear();
        boolean notBefore = LocalDate.parse("2016-06-12")
                .isBefore(LocalDate.parse("2016-06-11"));

        boolean isAfter = LocalDate.parse("2016-06-12")
                .isAfter(LocalDate.parse("2016-06-11"));
        LocalDateTime beginningOfDay = LocalDate.parse("2016-06-12").atStartOfDay();
        LocalDate firstDayOfMonth = LocalDate.parse("2016-06-12")
                .with(TemporalAdjusters.firstDayOfMonth());

        //Local Time
        LocalTime now = LocalTime.now();
        LocalTime sixThirty = LocalTime.parse("06:30");
        LocalTime sixThirty1 = LocalTime.of(6, 30);
        LocalTime sevenThirty = LocalTime.parse("06:30").plus(1, ChronoUnit.HOURS);
        boolean isbefore = LocalTime.parse("06:30").isBefore(LocalTime.parse("07:30"));
        LocalTime maxTime = LocalTime.MAX;

        //Local Date Time
        LocalDateTime.now();
        LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30);
        LocalDateTime.parse("2015-02-20T06:30:00");
//        LocalDateTime.plusDays(1);
//        LocalDateTime.minusHours(2);
//        localDateTime.getMonth();
//https://www.baeldung.com/java-8-date-time-intro

        System.out.println(twelve);
        System.out.println(sixThirty);
    }
}
