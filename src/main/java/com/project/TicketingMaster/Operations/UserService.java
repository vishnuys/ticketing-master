package com.project.TicketingMaster.Operations;

import com.project.TicketingMaster.Data.User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {

    private final AtomicLong userIdGenerator = new AtomicLong();
    private final Map<Long, User> users = new ConcurrentHashMap<>();

    public final User createUser(String firstName, String lastName, String email) {
        User newUser = new User(
                userIdGenerator.incrementAndGet(),
                firstName,
                lastName,
                email
        );
        users.put(newUser.getUserId(), newUser);
        return newUser;
    }
}
