package com.project.TicketingMaster.Data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SeatAllocation {

    private final Section section;
    private final Integer seatNumber;
}
