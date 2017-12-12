package com.mindcanary.infrastructure.users;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

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

		String sql = "INSERT INTO public.users( first_name, last_name, nickname, user_name, email, challenge_coins, firebase_uuid, bio) "
				+ "VALUES (:first_name, :last_name, :nickname, :user_name, :email, :challenge_coins, :firebase_uuid, :bio)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		namedParameterJdbcTemplate.update(sql, params, keyHolder);
		long id = Long.valueOf(keyHolder.getKeys().get("id").toString());
		user.setId(id);
		return user;
	}

	@Override
	public User getByUuid(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getByUuid(List<String> uuids) {
		// TODO Auto-generated method stub
		return null;
	}

}
