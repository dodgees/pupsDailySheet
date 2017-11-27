package com.mindcanary.config;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestScope;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.mindcanary.domain.ClientId;
import com.mindcanary.domain.authentication.AuthenticationDomainServiceImpl;
import com.mindcanary.domain.client.ClientIdDomainService;
import com.mindcanary.infrastructure.RequestScopedData;

@Named
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

	@Inject
	private RequestScopedData requestScopedData;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (request.getMethod().equals("OPTIONS")) {
			return true;
		}
		String idToken = request.getHeader("Firebase-Auth");
		logger.info("ID Token: " + idToken + " for endpoint:" + request.getRequestURL());
		FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdTokenAsync(idToken).get();
		String uid = decodedToken.getUid();
		logger.info("UID: " + uid);
		requestScopedData.setUid(uid);
		return true;
	}

}