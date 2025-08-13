package ru.flytickets;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.*;
import static java.util.Map.Entry;

public class MinFlightTimeByCarrier {

    public static void printSortedByMinTime(List<Ticket> tickets, String origin, String destination) {
        Map<String, Duration> map = minFlightTimeByCarrier(tickets, origin, destination);

        System.out.println("Минимальное время полёта между " + origin + " и " + destination + " для каждого перевозчика составляет:");
        map.entrySet().stream()
                .sorted(Comparator.comparing(Entry<String, Duration>::getValue)
                        .thenComparing(Entry::getKey))
                .forEach(e -> System.out.printf("%s = %s%n", e.getKey(), formatDurationSmart(e.getValue())));
    }

    public static Map<String, Duration> minFlightTimeByCarrier(List<Ticket> tickets, String origin, String destination) {
        Map<String, Duration> result = new HashMap<>();

        List<Ticket> filtered = new ArrayList<>();
        for (Ticket t : tickets) {
            if (origin.equalsIgnoreCase(t.getOrigin()) &&
                    destination.equalsIgnoreCase(t.getDestination())) {
                filtered.add(t);
            }
        }

        for (Ticket t : filtered) {
            String carrier = t.getCarrier();

            ZonedDateTime departure = ZonedDateTime.of(
                    t.getDeparture_date(),
                    t.getDeparture_time(),
                    AirportGMTService.getAirportGMT(t.getOrigin()));

            ZonedDateTime arrival = ZonedDateTime.of(
                    t.getArrival_date(),
                    t.getArrival_time(),
                    AirportGMTService.getAirportGMT(t.getDestination()));

            Duration flightTime = Duration.between(departure, arrival);

            if (!result.containsKey(carrier) || flightTime.compareTo(result.get(carrier)) < 0) {
                result.put(carrier, flightTime);
            }
        }

        return result;
    }

    public static String formatDurationSmart(Duration d) {
        long days = d.toDays();
        long hours = d.minusDays(days).toHours();
        long minutes = d.minusDays(days).minusHours(hours).toMinutes();
        StringBuilder sb = new StringBuilder();
        if (days > 0) sb.append(days).append(" д, ");
        sb.append(hours).append(" ч, ").append(minutes).append(" м.");
        return sb.toString();
    }
}
