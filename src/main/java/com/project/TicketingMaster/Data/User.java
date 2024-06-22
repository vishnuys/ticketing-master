package com.project.TicketingMaster.Data;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private final Long userId;
    private final String firstName;
    private final String lastName;
    private final String email;

}
