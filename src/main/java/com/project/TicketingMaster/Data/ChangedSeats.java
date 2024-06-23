package com.project.TicketingMaster.Data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * POJO for changed seat details
 */
@AllArgsConstructor
@Data
public class ChangedSeats {

    private final User user;
    private final SeatAllocation previousSeat;
    private final SeatAllocation newSeat;
    private final Map<String, String> newReceipt;

    /**
     * Gets seat change details with new receipt
     * @return Changed Seat details
     */
    public final Map<String, Object> getChangedSeats() {
        Map<String, Object> changedSeats = new HashMap<>();
        changedSeats.put("User Name", this.getUser().getFullName());
        changedSeats.put("Previous Seat", this.getPreviousSeat().getSeatNumber());
        changedSeats.put("New Seat", this.getNewSeat().getSeatNumber());
        changedSeats.put("New Receipt", this.getNewReceipt());
        return changedSeats;
    }
}
