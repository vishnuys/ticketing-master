package com.project.TicketingMaster.Operations;

import com.project.TicketingMaster.Constants.Location;
import com.project.TicketingMaster.Data.PurchaseRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class RequestService {
    private boolean validateLocation(String location) {
        try {
            Location.valueOf(location.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean validateEmail(String email) {
        String regexPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }

    public List<String> validatePurchaseRequest(PurchaseRequest request) {
        List<String> invalidParams = new ArrayList<>();
        boolean isEmailValid = validateEmail(request.getEmail());
        boolean isFromValid = validateLocation(request.getFrom());
        boolean isToValid = validateLocation(request.getTo());

        if(!isEmailValid) {
            invalidParams.add("Email");
        }
        if (!isFromValid) {
            invalidParams.add("From");
        }
        if (!isToValid) {
            invalidParams.add("To");
        }
        return invalidParams;
    }
}
