package ru.netology.repository;

import ru.netology.domain.NotFoundException;
import ru.netology.domain.Ticket;

public class TicketRepository {
    Ticket[] tickets = new Ticket[0];

    public void save(Ticket item) {
        int length = tickets.length + 1;
        Ticket[] tmp = new Ticket[length];
        System.arraycopy(tickets, 0, tmp, 0, tickets.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        tickets = tmp;
    }

    public Ticket[] findAll() {
        return tickets;
    }

    public Ticket findByID(int id) {
        for (Ticket item : tickets) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void removeByID(int id) throws NotFoundException {
        if (findByID(id) != null) {
            int length = tickets.length - 1;
            Ticket[] tmp = new Ticket[length];
            int index = 0;
            for (Ticket item : tickets) {
                if (item.getId() != id) {
                    tmp[index] = item;
                    index++;
                }
                tickets = tmp;
            }
        } else {
            throw new NotFoundException("Element with ID " + id + " not found");
        }
    }
}