package ru.flytickets;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static ru.flytickets.WriteInFile.writeInFile;

public class PriceStats {

    public static void printMeanMedianDiff(List<Ticket> tickets, String origin, String destination) throws IOException {
        OptionalDouble mean   = meanPrice(tickets, origin, destination);
        OptionalDouble median = medianPrice(tickets, origin, destination);
        OptionalDouble diff   = meanMinusMedian(tickets, origin, destination);

        String lineSep = System.lineSeparator();
        StringBuilder sb = new StringBuilder();

        if (mean.isEmpty()) {
            String msg = "По направлению " + origin + " → " + destination + " билетов не найдено." + lineSep;
            System.out.print(msg);
            writeInFile(new StringBuilder(msg), true);
            return;
        }

        String header = String.format("Статистика цен по направлению %s → %s:%n", origin, destination);
        System.out.print(header);
        sb.append(header);

        String meanStr   = String.format(Locale.ROOT, "Средняя цена: %.0f%n", mean.getAsDouble());
        String medianStr = String.format(Locale.ROOT, "Медиана: %.0f%n",  median.getAsDouble());
        String diffStr   = String.format(Locale.ROOT, "Разница (средняя − медиана): %.0f%n", diff.orElse(0));

        System.out.print(meanStr);
        System.out.print(medianStr);
        System.out.print(diffStr);

        sb.append(meanStr).append(medianStr).append(diffStr);
        writeInFile(sb, true);
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
