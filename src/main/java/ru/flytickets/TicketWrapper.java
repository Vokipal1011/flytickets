package ru.flytickets;

import java.util.List;

public class TicketWrapper {

    private List<Ticket> tickets;

    public TicketWrapper() {}

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
