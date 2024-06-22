package com.project.TicketingMaster.Operations;

import com.project.TicketingMaster.Data.User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    private final Map<String, User> users = new ConcurrentHashMap<>();

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
}
