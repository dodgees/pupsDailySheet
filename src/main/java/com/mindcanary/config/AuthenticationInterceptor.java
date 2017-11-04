package com.mindcanary.config;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mindcanary.domain.ClientId;
import com.mindcanary.domain.authentication.AuthenticationDomainServiceImpl;
import com.mindcanary.domain.client.ClientIdDomainService;

@Named
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

	@Inject
	private AuthenticationDomainServiceImpl authenticationDomainService;

	@Inject
	private ClientIdDomainService clientIdDomainService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (request.getMethod().equals("OPTIONS")) {
			return true;
		}
		String clientId = request.getHeader("Client-Id");

		logger.info("Client-ID: " + clientId + " for endpoint:" + request.getRequestURL());
		if (clientId != null && !clientId.isEmpty()) {
			boolean isValidClient = authenticationDomainService.isValidClient(clientId);
			validateRoleAndUrl(request);
			return isValidClient;
		}
		// JOE - change this to return false to turn on client authentication
		return true;
	}

	private void validateRoleAndUrl(HttpServletRequest request) {
		String clientId = request.getHeader("Client-Id");
		ClientId fullClientId = clientIdDomainService.getByClientId(clientId);
		if (fullClientId.getRole() != 1 && request.getRequestURI().startsWith("/clients")) {
			throw new RuntimeException("You do not have access to see client Ids");
		}

	}

	private String getCookieValueByName(String name, Cookie[] cookies) {
		if (cookies == null) {
			logger.info("No Cookies Available.");
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(name)) {
				logger.info("Found ClientId: " + cookie.getValue());
				return cookie.getValue();
			}
		}
		logger.info("Cookie: " + name + "-not found.");
		return null;

	}

}