package com.mindcanary.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.mindcanary.exceptions.AuthenticationException;
import com.mindcanary.infrastructure.RequestScopedData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutionException;

@Named
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    @Inject
    private RequestScopedData requestScopedData;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
        String idToken = request.getHeader("Firebase-Auth");
        logger.info("ID Token: " + idToken + " for endpoint:" + request.getRequestURL());
        FirebaseToken decodedToken;
//        try {
//            decodedToken = FirebaseAuth.getInstance().verifyIdTokenAsync(idToken).get();
//            String uid = decodedToken.getUid();
//            logger.info("UID: " + uid);
//            requestScopedData.setFirebaseToken(decodedToken);
            return true;
//        } catch (InterruptedException | ExecutionException e) {
//            throw new AuthenticationException(e.getMessage());
//        }
    }

}