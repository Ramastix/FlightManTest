package ru.netology.manager;

import ru.netology.domain.NotFoundException;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

public class TicketManager {
    TicketRepository repository;

    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }

    public void add(Ticket item) {
        repository.save(item);
    }

    public Ticket[] getAll() {
        return repository.findAll();
    }

    public Ticket[] findAll(String from, String to) {
        Ticket[] result = new Ticket[0];

        for (Ticket item : repository.findAll()) {
            if (item.matches(from, to)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = item;
                result = tmp;
            }
        }

        Arrays.sort(result);
        return result;
    }

    public Ticket findByID(int id) {
        return repository.findByID(id);
    }

    public void removeByID(int id) {
        repository.removeByID(id);
    }
}