package com.project.TicketingMaster.Operations;

import com.project.TicketingMaster.Constants.Price;
import com.project.TicketingMaster.Data.SeatAllocation;
import com.project.TicketingMaster.Data.Section;
import com.project.TicketingMaster.Data.Ticket;
import com.project.TicketingMaster.Data.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TicketService {

    private final Map<Long, Ticket> tickets = new ConcurrentHashMap<>();
    private final Set<Integer> assignedSeats = ConcurrentHashMap.newKeySet();
    private final AtomicLong receiptNumberGenerator = new AtomicLong();

    public Optional<Ticket> purchaseTicket(String from, String to, User user) {
        if (assignedSeats.size() < 100) {
            Ticket newTicket = new Ticket(
                    receiptNumberGenerator.incrementAndGet(),
                    user,
                    from,
                    to,
                    allocateSeat(),
                    Price.getPrice(from, to)
            );
            tickets.put(newTicket.getReceiptNumber(), newTicket);
            return Optional.of(newTicket);
        }
        return Optional.empty();
    }

    public List<Map<String, String>> getReceiptsForUser(String email) {
        List<Ticket> userTickets = getTicketsForUser(email);
        return userTickets.stream().map(Ticket::getReceipt).toList();
    }

    private List<Ticket> getTicketsForUser(String email) {
        List<Ticket> userTickets = new ArrayList<>();
        for (Map.Entry<Long, Ticket> ticket: tickets.entrySet()) {
            if (ticket.getValue().getUser().getEmail().equals(email)) {
                userTickets.add(ticket.getValue());
            }
        }
        return userTickets;
    }

    private SeatAllocation allocateSeat() {
        int seatNumber = (int) Math.ceil(Math.random() * 100);
        while(assignedSeats.contains(seatNumber)) {
            seatNumber = (int) Math.ceil(Math.random() * 100);
        }
        assignedSeats.add(seatNumber);
        Section section = seatNumber < 50 ? Section.SECTION_A : Section.SECTION_B;
        return new SeatAllocation(section, seatNumber);
    }

    public Optional<Ticket> getTicket(Long receiptNumber) {
        if (tickets.containsKey(receiptNumber)) {
            return Optional.of(tickets.get(receiptNumber));
        }
        return Optional.empty();
    }

    public List<Ticket> removeTicketsForUser(String email) {
        List<Ticket> userTickets = getTicketsForUser(email);
        for (Ticket ticket: userTickets) {
            assignedSeats.remove(ticket.getSeatAllocation().getSeatNumber());
            tickets.remove(ticket.getReceiptNumber());
        }
        return userTickets;
    }
}
