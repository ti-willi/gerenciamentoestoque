package com.tiwilli.gerenciamentoestoque.util;

import org.springframework.data.util.Pair;

import java.time.*;

public class Utils {

    public static Pair<Instant, Instant> dateRange(String period, Instant nowInstant) {
        ZoneId zone = ZoneId.systemDefault();
        LocalDate now = nowInstant.atZone(zone).toLocalDate();

        return switch (period.toLowerCase()) {
            case "week" -> {
                LocalDate start = now.with(DayOfWeek.MONDAY);
                LocalDate end = now.with(DayOfWeek.SUNDAY);
                yield Pair.of(
                        start.atStartOfDay(zone).toInstant(),
                        end.atTime(LocalTime.MAX).atZone(zone).toInstant()
                );
            }
            case "month" -> {
                LocalDate start = now.withDayOfMonth(1);
                LocalDate end = now.withDayOfMonth(now.lengthOfMonth());
                yield Pair.of(
                        start.atStartOfDay(zone).toInstant(),
                        end.atTime(LocalTime.MAX).atZone(zone).toInstant()
                );
            }
            case "year" -> {
                LocalDate start = now.withDayOfYear(1);
                LocalDate end = now.withDayOfYear(now.lengthOfYear());
                yield Pair.of(
                        start.atStartOfDay(zone).toInstant(),
                        end.atTime(LocalTime.MAX).atZone(zone).toInstant()
                );
            }
            default -> null;
        };
    }

}
