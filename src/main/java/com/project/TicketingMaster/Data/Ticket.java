package com.project.TicketingMaster.Data;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * POJO for Ticket
 */
@Data
@AllArgsConstructor
public class Ticket {
    private final Long receiptNumber;
    private final User user;
    private final String from;
    private final String to;
    private SeatAllocation seatAllocation;
    private final BigDecimal price;

    /**
     * @return Ticket details in JSON format
     */
    public Map<String, String> getReceipt() {
        Map<String, String> receipt = new HashMap<>();
        receipt.put("Receipt Number", this.getReceiptNumber().toString());
        receipt.put("Passenger Name", this.user.getFullName());
        receipt.put("Passenger Email", this.user.getEmail());
        receipt.put("From", this.from);
        receipt.put("To", this.to);
        receipt.put("Seat Section", this.seatAllocation.getSection().sectionName);
        receipt.put("Seat Number", this.seatAllocation.getSeatNumber().toString());
        receipt.put("Ticket Price", this.getPrice().toString());
        return receipt;
    }
}
