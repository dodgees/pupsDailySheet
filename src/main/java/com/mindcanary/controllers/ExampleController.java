package com.mindcanary.controllers;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindcanary.domain.ExampleDomainService;
import com.mindcanary.domain.challenge.Challenge;
import com.mindcanary.domain.user.User;

@RestController
@RequestMapping("/example")
public class ExampleController {

	@Inject
	private ExampleDomainService exampleDomainService;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public Long testDatabaseConnection() {
		Long longValue = exampleDomainService.getLongFromDatabase();
		return longValue;
	}

	@RequestMapping(value = "/challenges", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody List<Challenge> getExampleChallenges() {
		User levi = new User(1,"Levi", "Liester");
		User mike = new User(2,"Mike", "Bumgarder");
		User sky = new User(3,"Sky", "Snyder");
		Challenge challenge1 = new Challenge(1,levi, sky, LocalDateTime.now(), "Square Root", "Math");
		Challenge challenge2 = new Challenge(2, levi, mike, LocalDateTime.now(), "Which President?", "History");
		Challenge challenge3 = new Challenge(3, mike, sky, LocalDateTime.now(), "Where in the world?", "Geography");
		Challenge challenge4 = new Challenge(4, sky, mike, LocalDateTime.now(), "Why is sky blue?", "Science");
		
		List<Challenge> challenges = Arrays.asList(challenge1, challenge2, challenge3, challenge4);
		
		return challenges;
	}

}
