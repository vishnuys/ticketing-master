package com.project.TicketingMaster.Operations;

import com.project.TicketingMaster.Data.Ticket;
import com.project.TicketingMaster.Data.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@AllArgsConstructor
public class UserService {

    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final TicketService ticketService;

    public final User createUser(String firstName, String lastName, String email) {
        User newUser = new User(
                firstName,
                lastName,
                email
        );
        users.put(newUser.getEmail(), newUser);
        return newUser;
    }

    public final Optional<User> getUser(String email) {
        if (users.containsKey(email)) {
            return Optional.of(users.get(email));
        }
        return Optional.empty();
    }

    public Optional<Map<String, String>> removeUser(String email) {
        if (users.containsKey(email)) {
            User removedUser = users.get(email);
            users.remove(email);
            return Optional.of(removedUser.getUserDetails());
        }
        return Optional.empty();
    }


    public Map<String, Object> removeTicketsForUser(String email) {
        List<Ticket> removedTickets = ticketService.removeTicketsForUser(email);
        List<Map<String, String>> removedReceipts = removedTickets.stream().map(Ticket::getReceipt).toList();
        Optional<Map<String, String>> removedUser = removeUser(email);
        return Map.of(
                "Removed Tickets", removedReceipts,
                "Removed User", removedUser.orElse(Collections.EMPTY_MAP));
    }
}
