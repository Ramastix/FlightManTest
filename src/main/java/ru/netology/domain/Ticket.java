package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Ticket implements Comparable<Ticket> {
    int id;
    int cost;
    String startAirport;
    String endAirport;
    int travelTime;


    public boolean matches(String from, String to) {
        return (this.startAirport.equalsIgnoreCase(from)) && (this.endAirport.equalsIgnoreCase(to));
    }

    @Override
    public int compareTo(Ticket o) {
        return cost - o.cost;
    }
}