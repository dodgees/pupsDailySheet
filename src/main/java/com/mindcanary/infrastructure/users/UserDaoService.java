package com.mindcanary.infrastructure.users;

import java.util.List;

import com.mindcanary.domain.user.User;
import com.mindcanary.domain.user.UserSearch;

public interface UserDaoService {

	User create(User user);

	User getByUuid(String uuid);

	List<User> getByUuid(List<String> uuids);

    List<UserSearch> searchByName(String searchText);
}
