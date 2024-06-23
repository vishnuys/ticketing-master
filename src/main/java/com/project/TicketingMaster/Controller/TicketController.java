package com.project.TicketingMaster.Controller;

import com.project.TicketingMaster.Data.Section;
import com.project.TicketingMaster.Data.Ticket;
import com.project.TicketingMaster.Data.User;
import com.project.TicketingMaster.Operations.RequestService;
import com.project.TicketingMaster.Operations.TicketService;
import com.project.TicketingMaster.Operations.UserService;
import com.project.TicketingMaster.Requests.PurchaseRequest;
import com.project.TicketingMaster.Requests.ReceiptRequest;
import com.project.TicketingMaster.Requests.SectionRequest;
import com.project.TicketingMaster.Requests.UserRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controller for Ticket Management
 */
@RestController
@AllArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final UserService userService;
    private final RequestService requestService;

    /**
     * Creates ticket for given user details
     * @param request Purchase Ticket Request
     * @return Receipt for created ticket
     */
    @PostMapping("purchase-ticket")
    public Map<String, String> purchaseTicketHandler(@RequestBody PurchaseRequest request) {
        List<String> invalidParams = requestService.validatePurchaseRequest(request);
        if (invalidParams.isEmpty()) {
            User user = userService.createUser(request.getFirstName(), request.getLastName(), request.getEmail());
            Optional<Ticket> ticket = ticketService.purchaseTicket(request.getFrom(), request.getTo(), user);
            if (ticket.isPresent()) {
                return ticket.get().getReceipt();
            } else {
                throw new ResponseStatusException(
                        HttpStatus.NOT_ACCEPTABLE, "No Seats Available");
            }
        } else {
            String invalidParamString = invalidParams.toString();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, String.format("Invalid Params: %s", invalidParamString));
        }
    }

    /**
     * Gets receipt for given receipt number
     * @param request Receipt request with Receipt number
     * @return Receipt of given receipt number
     */
    @PostMapping("get-receipt")
    public Map<String,String> getReceiptHandler(@RequestBody ReceiptRequest request) {
        Optional<Ticket> ticket = ticketService.getTicket(request.getReceiptNumber());
        if (ticket.isPresent()) {
            return ticket.get().getReceipt();
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid Receipt Number");
        }
    }

    /**
     * Gets all receipts for given user
     * @param request containing email of the user
     * @return List of receipts for the given user
     */
    @PostMapping("get-user-receipts")
    public List<Map<String, String>> getUserReceiptsHandler(@RequestBody UserRequest request) {
        return ticketService.getReceiptsForUser(request.getEmail());
    }

    /**
     * Change all seats for a given user
     * @param request containing user email
     * @return changed seats details for the given user
     */
    @PostMapping("change-seats")
    public List<Map<String, Object>> changeSeatsForUserHandler(@RequestBody UserRequest request) {
        return ticketService.changeSeatsForUser(request.getEmail());
    }

    /**
     * Get all users and seats assigned for the given section
     * @param request containing section
     * @return List of Users with their seat details
     */
    @PostMapping("get-users-by-section")
    public List<Map<String, String>> getUsersBySectionHandler(@RequestBody SectionRequest request) {
        try {
            Section requestSection = Section.of(request.getSection());
            return ticketService.getUsersBySection(requestSection);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid Section");
        }
    }
}
