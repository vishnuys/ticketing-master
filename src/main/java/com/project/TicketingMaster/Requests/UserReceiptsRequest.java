package com.project.TicketingMaster.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReceiptsRequest {

    @NonNull
    private String email;
}
