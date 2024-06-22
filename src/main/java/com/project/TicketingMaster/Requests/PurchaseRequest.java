package com.project.TicketingMaster.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseRequest {

    private final String from;
    private final String to;
    private final String firstName;
    private final String lastName;
    private final String email;
}
