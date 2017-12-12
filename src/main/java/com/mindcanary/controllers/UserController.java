package com.mindcanary.controllers;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindcanary.domain.user.User;
import com.mindcanary.domain.user.UserDomainService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Inject
	private UserDomainService userDomainService;

	@RequestMapping(value = "/{firebase_uuid}/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody User createUser(@PathVariable("firebase_uuid") String firebaseUuid) {
		User userToCreate = new User(firebaseUuid);
		User createdUser = userDomainService.create(userToCreate);
		return createdUser;
	}

	@RequestMapping(value = "/{firebase_uuid}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody User getUser(@PathVariable("firebase_uuid") String firebaseUuid) {
		User user = userDomainService.getByUuid(firebaseUuid);
		return user;
	}

}
