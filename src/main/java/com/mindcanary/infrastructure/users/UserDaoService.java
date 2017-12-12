package com.mindcanary.infrastructure.users;

import java.util.List;

import com.mindcanary.domain.user.User;

public interface UserDaoService {

	User create(User user);

	User getByUuid(String uuid);

	List<User> getByUuid(List<String> uuids);

}
