package com.mindcanary.domain.user;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.mindcanary.infrastructure.users.UserDaoService;

@Named
public class UserDomainServiceImpl implements UserDomainService {

	@Inject
	private UserDaoService userDaoService;

	@Override
	public User create(User user) {
		User createdUser = userDaoService.create(user);
		return createdUser;
	}

	@Override
	public User getByUuid(String uuid) {
		User user = userDaoService.getByUuid(uuid);
		return user;
	}

	@Override
	public List<User> getByUuid(List<String> uuids) {
		List<User> users = new ArrayList<>();
		for (String uuid : uuids) {
			User user = getByUuid(uuid);
			users.add(user);
		}
		return users;
	}

}
