package com.project.TicketingMaster.Constants;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public enum Price {
    LONDON_PARIS(20),
    PARIS_LONDON(20),
    LONDON_BERLIN(35),
    BERLIN_LONDON(35),
    PARIS_BERLIN(40),
    BERLIN_PARIS(40);


    private final Integer ticketPrice;

    public static BigDecimal getPrice(String from, String to) {
        Price price = Price.valueOf(from.toUpperCase() + "_" + to.toUpperCase());
        return new BigDecimal(price.ticketPrice);
    }
}
