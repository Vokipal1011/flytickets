package ru.flytickets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


import static ru.flytickets.MinFlightTimeByCarrier.printSortedByMinTime;

public class Main {
    public static void main(String[] args) throws IOException {

        Path jsonPath = Paths.get(System.getProperty("user.dir"), "tickets.json");

        String json = Files.readString(jsonPath);
        json = json.replace("\uFEFF", "");

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        TicketWrapper wrapper = mapper.readValue(json, TicketWrapper.class);
        List<Ticket> tickets = wrapper.getTickets();

        printSortedByMinTime(tickets, "VVO", "TLV");
        PriceStats.printMeanMedianDiff(tickets, "VVO", "TLV");
    }
}