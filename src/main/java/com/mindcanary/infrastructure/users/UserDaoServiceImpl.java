package com.mindcanary.infrastructure.users;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.mindcanary.domain.user.UserSearch;
import com.mindcanary.rowmappers.UserSearchRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mindcanary.domain.user.User;

@Named
public class UserDaoServiceImpl implements UserDaoService {

	@Inject
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public User create(User user) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("first_name", user.getFirstName());
		params.addValue("last_name", user.getLastName());
		params.addValue("nickname", user.getNickName());
		params.addValue("user_name", user.getUserName());
		params.addValue("email", user.getEmail());
		params.addValue("challenge_coins", user.getChallengeCoins());
		params.addValue("firebase_uuid", user.getFirebaseUuid());
		params.addValue("bio", user.getBio());

		String sql = "INSERT INTO users( first_name, last_name, nickname, user_name, email, challenge_coins, firebase_uuid, bio) "
				+ "VALUES (:first_name, :last_name, :nickname, :user_name, :email, :challenge_coins, :firebase_uuid, :bio)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		namedParameterJdbcTemplate.update(sql, params, keyHolder);
		long id = Long.valueOf(keyHolder.getKeys().get("id").toString());
		user.setId(id);
		return user;
	}

	@Override
	public User getByUuid(String uuid) {
		MapSqlParameterSource params = new MapSqlParameterSource("uuid", uuid);
		String sql = "Select * from users where firebase_uuid = :uuid";
		User user = namedParameterJdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
			long id = rs.getLong("id");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			String nickName = rs.getString("nickname");
			String userName = rs.getString("user_name");
			String email = rs.getString("email");
			long challengeCoins = rs.getLong("challenge_coins");
			String firebaseUuid = rs.getString("firebase_uuid");
			String bio = rs.getString("bio");
			User foundUser = new User(firebaseUuid);
			foundUser.setId(id);
			foundUser.setFirstName(firstName);
			foundUser.setLastName(lastName);
			foundUser.setNickName(nickName);
			foundUser.setUserName(userName);
			foundUser.setEmail(email);
			foundUser.setChallengeCoins(challengeCoins);
			foundUser.setBio(bio);
			return foundUser;
		});
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

	@Override
	public List<UserSearch> searchByName(String searchText) {
		MapSqlParameterSource params = new MapSqlParameterSource("searchText", searchText+'%');
		String sql = "Select firebase_uuid, first_name, last_name from users " +
				"where first_name like :searchText or last_name like :searchText";
		return namedParameterJdbcTemplate.query(sql, params, new UserSearchRowMapper());
	}

}
