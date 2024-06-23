package com.project.TicketingMaster.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * POJO for Receipt Request
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptRequest {

    @NonNull
    private Long receiptNumber;
}
