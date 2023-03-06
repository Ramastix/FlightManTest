package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {

    TicketRepository repository = new TicketRepository();
    TicketManager manager = new TicketManager(repository);
    Ticket route1 = new Ticket(1, 7500, "LED", "GOJ", 150);
    Ticket route2 = new Ticket(2, 1500, "GOJ", "LED", 150);
    Ticket route3 = new Ticket(3, 3500, "LED", "GOJ", 150);
    Ticket route4 = new Ticket(4, 1500, "KUF", "LED", 150);
    Ticket route5 = new Ticket(5, 1500, "LED", "KUF", 150);
    Ticket route6 = new Ticket(6, 5100, "LED", "GOJ", 150);

    @BeforeEach
    void setUp() {
        manager.add(route1);
        manager.add(route2);
        manager.add(route3);
        manager.add(route4);
        manager.add(route5);
        manager.add(route6);
    }

    @Test
    void shouldFindAllAndSortByCost() {
        Ticket[] actual = manager.findAll("LED", "GOJ");
        Ticket[] expected = {route3, route6, route1};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindAll() {
        Ticket[] actual = manager.findAll("FRU", "GOJ");
        Ticket[] expected = {};

        assertArrayEquals(expected, actual);
    }


    @Test
    void shouldAdd() {
        manager.add(route2);
        Ticket[] expected = {route1, route2, route3, route4, route5, route6, route2};
        Ticket[] actual = manager.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindByID() {
        Ticket expected = route4;
        Ticket actual = manager.findByID(4);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindByID() {
        Ticket expected = null;
        Ticket actual = manager.findByID(9);
        assertEquals(expected, actual);
    }

    @Test
    void shouldRemoveByID() {
        manager.removeByID(5);
        Ticket[] expected = {route1, route2, route3, route4, route6};
        Ticket[] actual = manager.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotRemoveByID() {
        assertThrows(NotFoundException.class, () -> manager.removeByID(15));
    }
}