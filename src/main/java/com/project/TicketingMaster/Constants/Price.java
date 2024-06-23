package com.project.TicketingMaster.Constants;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

/**
 * List of Price for each from to locations
 */
@AllArgsConstructor
public enum Price {
    LONDON_PARIS(20),
    PARIS_LONDON(20),
    LONDON_BERLIN(35),
    BERLIN_LONDON(35),
    PARIS_BERLIN(40),
    BERLIN_PARIS(40);


    private final Integer ticketPrice;

    /**
     * Get price for given From to locations
     * @param from
     * @param to
     * @return Price of the ticket for given locations
     */
    public static BigDecimal getPrice(String from, String to) {
        Price price = Price.valueOf(from.toUpperCase() + "_" + to.toUpperCase());
        return new BigDecimal(price.ticketPrice);
    }
}
