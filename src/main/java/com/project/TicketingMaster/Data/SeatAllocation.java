package com.project.TicketingMaster.Data;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Seat Allocation POJO
 */
@Data
@AllArgsConstructor
public class SeatAllocation {

    private final Section section;
    private final Integer seatNumber;
}
