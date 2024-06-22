package com.project.TicketingMaster.Controller;

import com.project.TicketingMaster.Data.User;
import com.project.TicketingMaster.Operations.UserService;
import com.project.TicketingMaster.Requests.UserRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;


@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("get-user")
    public Map<String, String> getUserHandler(@RequestBody UserRequest request) {
        System.out.println(request.toString());
        Optional<User> user = userService.getUser(request.getEmail());
        if (user.isPresent()) {
            return user.get().getUserDetails();
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "User with the given email does not exist");
        }

    }
}
