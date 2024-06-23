package com.project.TicketingMaster.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

/**
 * POJO for Purchase Request
 */
@Data
@AllArgsConstructor
public class PurchaseRequest {

    @NonNull
    private final String from;

    @NonNull
    private final String to;

    @NonNull
    private final String firstName;

    @NonNull
    private final String lastName;

    @NonNull
    private final String email;
}
