package ru.flytickets;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;


public class Ticket {
    private String origin;
    private String origin_name;
    private String destination;
    private String destination_name;

    @JsonFormat(pattern = "dd.MM.yy")
    private LocalDate departure_date;

    @JsonFormat(pattern = "H:mm")
    private LocalTime departure_time;

    @JsonFormat(pattern = "dd.MM.yy")
    private LocalDate arrival_date;

    @JsonFormat(pattern = "H:mm")
    private LocalTime arrival_time;
    private String carrier;
    private int stops;
    private double price;

    public Ticket() {
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOrigin_name() {
        return origin_name;
    }

    public void setOrigin_name(String origin_name) {
        this.origin_name = origin_name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestination_name() {
        return destination_name;
    }

    public void setDestination_name(String destination_name) {
        this.destination_name = destination_name;
    }

    public LocalDate getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(LocalDate departure_date) {
        this.departure_date = departure_date;
    }

    public LocalTime getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(LocalTime departure_time) {
        this.departure_time = departure_time;
    }

    public LocalDate getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(LocalDate arrival_date) {
        this.arrival_date = arrival_date;
    }

    public LocalTime getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(LocalTime arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public int getStops() {
        return stops;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

//JSON TICKET
//"origin": "VVO",
//    "origin_name": "Владивосток",
//    "destination": "TLV",
//    "destination_name": "Тель-Авив",
//    "departure_date": "12.05.18",
//    "departure_time": "6:10",
//    "arrival_date": "12.05.18",
//    "arrival_time": "16:15",
//    "carrier": "S7",
//    "stops": 0,
//    "price": 17400