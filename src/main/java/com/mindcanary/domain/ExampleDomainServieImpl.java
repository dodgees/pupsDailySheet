package com.mindcanary.domain;

import javax.inject.Inject;
import javax.inject.Named;

import com.mindcanary.infrastructure.example.ExampleDaoService;

@Named
public class ExampleDomainServieImpl implements ExampleDomainService {

	@Inject
	private ExampleDaoService exampleDaoService;

	@Override
	public Long getLongFromDatabase() {
		Long longValue = exampleDaoService.getLongFromDatabase();
		return longValue;
	}

}