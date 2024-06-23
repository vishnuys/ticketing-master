package com.project.TicketingMaster.Operations;

import com.project.TicketingMaster.Constants.Location;
import com.project.TicketingMaster.Requests.PurchaseRequest;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Validates Requests served by Ticketing Master
 */
@Service
public class RequestService {

    /**
     * Validates location ensuring the from to locations are served by Ticketing Master
     * @param location from to locations
     * @return boolean indicating location is valid or not
     */
    private boolean validateLocation(String location) {
        try {
            Location.valueOf(location.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Validates user email
     * @param email email to be verified
     * @return boolean indicating validity of the email entered
     */
    private boolean validateEmail(String email) {
        String regexPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }

    /**
     * Validates purchase ticket request by the User
     * @param request containing purchase request details
     * @return List of invalid parameters in the request
     */
    public List<String> validatePurchaseRequest(PurchaseRequest request) {
        List<String> invalidParams = new ArrayList<>();
        boolean isEmailValid = validateEmail(request.getEmail());
        boolean isFromValid = validateLocation(request.getFrom());
        boolean isToValid = validateLocation(request.getTo());
        boolean isFirstNameValid = Strings.isNotEmpty(request.getFirstName());
        boolean isLastNameValid = Strings.isNotEmpty(request.getLastName());

        if(!isEmailValid) {
            invalidParams.add("Email");
        }
        if (!isFromValid) {
            invalidParams.add("From");
        }
        if (!isToValid) {
            invalidParams.add("To");
        }
        if(!isFirstNameValid) {
            invalidParams.add("First Name");
        }
        if(!isLastNameValid) {
            invalidParams.add("Last Name");
        }
        return invalidParams;
    }
}
