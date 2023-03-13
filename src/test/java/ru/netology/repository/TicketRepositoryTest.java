package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class TicketRepositoryTest {

    @Test
    void shouldCatchException() {
        TicketRepository r = new TicketRepository();
        assertThrows(NotFoundException.class, () -> r.removeByID(6));
    }
}