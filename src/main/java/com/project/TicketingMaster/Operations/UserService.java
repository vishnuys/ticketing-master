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

/**
 * Service to handle User operations
 */
@Service
@AllArgsConstructor
public class UserService {

    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final TicketService ticketService;

    /**
     * Creaates user based on given user details
     * @param firstName
     * @param lastName
     * @param email
     * @return User created
     */
    public final User createUser(String firstName, String lastName, String email) {
        User newUser = new User(
                firstName,
                lastName,
                email
        );
        users.put(newUser.getEmail(), newUser);
        return newUser;
    }


    /**
     * Gets user details of given email
     * @param email of the user
     * @return User if exists in User Map
     */
    public final Optional<User> getUser(String email) {
        if (users.containsKey(email)) {
            return Optional.of(users.get(email));
        }
        return Optional.empty();
    }

    /**
     * Removes user from the user list
     * @param email of the user to remove
     * @return Removed user details
     */
    public Optional<Map<String, String>> removeUser(String email) {

        Optional<User> removedUser = getUser(email);
        if (removedUser.isPresent()) {
            users.remove(removedUser.get().getEmail());
            return Optional.of(removedUser.get().getUserDetails());
        }
        return Optional.empty();
    }

    /**
     * Removes all tickets for given user and user from user list
     * @param email of the user to remove
     * @return Removed tickets and user details
     */
    public Map<String, Object> removeTicketsForUser(String email) {
        List<Ticket> removedTickets = ticketService.removeTicketsForUser(email);
        List<Map<String, String>> removedReceipts = removedTickets.stream().map(Ticket::getReceipt).toList();
        Optional<Map<String, String>> removedUser = removeUser(email);
        return Map.of(
                "Removed Tickets", removedReceipts,
                "Removed User", removedUser.orElse(Collections.EMPTY_MAP));
    }
}
