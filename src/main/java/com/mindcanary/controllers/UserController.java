package com.mindcanary.controllers;

import com.google.firebase.auth.FirebaseToken;
import com.mindcanary.domain.user.User;
import com.mindcanary.domain.user.UserDomainService;
import com.mindcanary.domain.user.UserSearch;
import com.mindcanary.infrastructure.RequestScopedData;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Inject
    private RequestScopedData requestScopedData;

    @Inject
    private UserDomainService userDomainService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody
    User createUser() {
        // This logic is to create a user if one isn't found - Levi
        try {
            User foundUser = userDomainService.getByUuid(requestScopedData.getFirebaseToken().getUid());
            return foundUser;
        } catch (EmptyResultDataAccessException ex) {
            FirebaseToken token = requestScopedData.getFirebaseToken();
            User userToCreate = new User();
            if (token.getName() != null) {
                userToCreate.setFirstName(token.getName().split(" ")[0]);
                userToCreate.setLastName(token.getName().split(" ")[1]);
            }
            userToCreate.setEmail(token.getEmail());
            userToCreate.setFirebaseUuid(token.getUid());
            User createdUser = userDomainService.create(userToCreate);
            return createdUser;
        }
    }

    @RequestMapping(value = "/{firebase_uuid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody
    User getUser(@PathVariable("firebase_uuid") String firebaseUuid) {
        User user = userDomainService.getByUuid(firebaseUuid);
        return user;
    }

    @RequestMapping(value = "/search/{searchString}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody
    List<UserSearch> search(@PathVariable("searchString") String searchString) {
        return userDomainService.searchByName(searchString);
    }

}
