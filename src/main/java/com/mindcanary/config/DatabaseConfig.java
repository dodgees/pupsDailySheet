package com.mindcanary.config;

import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfig {

	@Inject
	private Environment env;

	private HikariDataSource perryDataSource = null;

	@Bean
	public HikariDataSource dataSource() throws URISyntaxException {
		if (perryDataSource == null) {

			URI dbUri = new URI(env.getProperty("DATABASE_URL"));

			String username = dbUri.getUserInfo().split(":")[0];
			System.out.println("username:"+username);
			String password = dbUri.getUserInfo().split(":")[1];
			System.out.println("password:"+password);
			String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath()
					+ "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			System.out.println("dbUrl:"+dbUrl);
			//I have no idea why I need this ssl=true ect but if you remove it you will get the following error
			//Caused by: org.postgresql.util.PSQLException: FATAL: no pg_hba.conf entry for host "72.213.49.57", user "xbddkeqdrgqvay", database "d67770ocvq7m7q", SSL off
			// So don't do that

			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(dbUrl);
			config.setUsername(username);
			config.setPassword(password);

			config.setDriverClassName("org.postgresql.Driver");
			config.setPoolName("Perrys Towing Connection Pool");
			config.setMaximumPoolSize(Integer.valueOf(env.getProperty("MAX_DB_CONNECTIONS")));

			perryDataSource = new HikariDataSource(config);
			perryDataSource.setConnectionTestQuery("Select 1 from DUAL");
			return perryDataSource;
		} else {
			return perryDataSource;
		}
	}

	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) throws URISyntaxException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		return template;
	}
}
