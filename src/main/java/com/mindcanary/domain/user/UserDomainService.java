package com.mindcanary.domain.user;

import java.util.List;

public interface UserDomainService {

	User create(User user);

	User getByUuid(String uuid);

	List<User> getByUuid(List<String> uuids);

    List<UserSearch> searchByName(String searchString);
}
