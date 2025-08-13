package ru.flytickets;

import java.util.*;
import java.util.stream.Collectors;

public final class PriceStats {

    private PriceStats() {}

    public static void printMeanMedianDiff(List<Ticket> tickets, String origin, String destination) {
        OptionalDouble mean = meanPrice(tickets, origin, destination);
        OptionalDouble median = medianPrice(tickets, origin, destination);
        OptionalDouble diff = meanMinusMedian(tickets, origin, destination);

        if (mean.isEmpty()) {
            System.out.println("По направлению " + origin + " → " + destination + " билетов не найдено.");
            return;
        }
        System.out.printf(
                Locale.ROOT,
                "Средняя цена: %.0f%nМедиана: %.0f%nРазница (средняя − медиана): %.0f%n",
                mean.getAsDouble(), median.getAsDouble(), diff.orElse(0)
        );
    }

    public static OptionalDouble meanPrice(List<Ticket> tickets, String origin, String destination) {
        return tickets.stream()
                .filter(t -> origin.equalsIgnoreCase(t.getOrigin()) &&
                        destination.equalsIgnoreCase(t.getDestination()))
                .mapToDouble(Ticket::getPrice)
                .average();
    }

    public static OptionalDouble medianPrice(List<Ticket> tickets, String origin, String destination) {
        List<Double> prices = tickets.stream()
                .filter(t -> origin.equalsIgnoreCase(t.getOrigin()) &&
                        destination.equalsIgnoreCase(t.getDestination()))
                .map(Ticket::getPrice)
                .sorted()
                .collect(Collectors.toList());

        int n = prices.size();
        if (n == 0) return OptionalDouble.empty();
        if (n % 2 == 1) {
            return OptionalDouble.of(prices.get(n / 2));
        } else {
            double m = (prices.get(n / 2 - 1) + prices.get(n / 2)) / 2.0;
            return OptionalDouble.of(m);
        }
    }


    public static OptionalDouble meanMinusMedian(List<Ticket> tickets, String origin, String destination) {
        OptionalDouble mean = meanPrice(tickets, origin, destination);
        OptionalDouble median = medianPrice(tickets, origin, destination);
        if (mean.isEmpty() || median.isEmpty()) return OptionalDouble.empty();
        return OptionalDouble.of(mean.getAsDouble() - median.getAsDouble());
    }

}
