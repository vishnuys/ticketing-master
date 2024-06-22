package com.project.TicketingMaster.Data;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class User {
    
    private final String firstName;
    private final String lastName;
    private final String email;

    public final String getFullName() {
        return this.getFirstName() + " " + this.getFirstName();
    }

    public final Map<String, String> getUserDetails() {
        Map<String, String> user = new HashMap<>();
        user.put("First Name", this.getFirstName());
        user.put("Last Name", this.getLastName());
        user.put("Email", this.getEmail());
        return user;
    }
}
