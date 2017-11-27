package com.mindcanary.config;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;


@Configuration
public class FirebaseConfig {

	@Inject
	private Environment env;

	@Bean
	public FirebaseApp firebaseApp() throws IOException {
		InputStream serviceAccount = MainApplication.class.getResourceAsStream("/serviceAccountKey.json");

		FirebaseOptions options = new FirebaseOptions.Builder()
		  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
		  .build();

		return FirebaseApp.initializeApp(options);
	}

}
