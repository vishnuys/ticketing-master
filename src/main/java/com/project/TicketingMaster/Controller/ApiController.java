package com.project.TicketingMaster.Controller;

import com.project.TicketingMaster.Data.PurchaseRequest;
import com.project.TicketingMaster.Data.Ticket;
import com.project.TicketingMaster.Data.User;
import com.project.TicketingMaster.Operations.RequestService;
import com.project.TicketingMaster.Operations.TicketService;
import com.project.TicketingMaster.Operations.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class ApiController {

    private final TicketService ticketService;
    private final UserService userService;
    private final RequestService requestService;

    @PostMapping("purchase-ticket")
    public Map<String, String> purchaseTicketHandler(
            @RequestBody PurchaseRequest request) {
        List<String> invalidParams = requestService.validatePurchaseRequest(request);
        if (invalidParams.isEmpty()) {
            User user = userService.createUser(request.getFirstName(), request.getLastName(), request.getEmail());
            Ticket ticket = ticketService.purchaseTicket(request.getFrom(), request.getTo(), user);
            return ticket.getReceipt();
        } else {
            String invalidParamString = invalidParams.toString();
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, String.format("Invalid Params: %s", invalidParamString));
        }
    }
}
