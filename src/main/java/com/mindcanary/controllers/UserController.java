package com.mindcanary.controllers;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import com.mindcanary.domain.user.UserSearch;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindcanary.domain.user.User;
import com.mindcanary.domain.user.UserDomainService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

	@Inject
	private UserDomainService userDomainService;

	@RequestMapping(value = "/{firebase_uuid}/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody User createUser(@PathVariable("firebase_uuid") String firebaseUuid) {
		User foundUser = userDomainService.getByUuid(firebaseUuid);
		if (foundUser != null) { // not sure this is the best approach - Levi
			return foundUser;
		}
		User userToCreate = new User(firebaseUuid);
		User createdUser = userDomainService.create(userToCreate);
		return createdUser;
	}

	@RequestMapping(value = "/{firebase_uuid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody User getUser(@PathVariable("firebase_uuid") String firebaseUuid) {
		User user = userDomainService.getByUuid(firebaseUuid);
		return user;
	}

	@RequestMapping(value = "/search/{searchString}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody List<UserSearch> search(@PathVariable("searchString") String searchString) {
		return userDomainService.searchByName(searchString);
	}

}
