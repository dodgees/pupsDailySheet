package com.mindcanary.controllers;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindcanary.domain.ExampleDomainService;

@RestController
@RequestMapping("/example")
public class ExampleController {

	@Inject
	private ExampleDomainService exampleDomainService;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public Long getById() {
		Long longValue = exampleDomainService.getLongFromDatabase();
		return longValue;
	}

}
