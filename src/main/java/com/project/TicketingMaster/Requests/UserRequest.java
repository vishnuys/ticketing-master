package com.project.TicketingMaster.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * POJO for User Request
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NonNull
    private String email;
}
