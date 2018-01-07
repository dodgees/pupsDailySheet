package com.mindcanary.infrastructure;

import com.google.firebase.auth.FirebaseToken;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import javax.inject.Named;

@Named
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopedData {
    private FirebaseToken firebaseToken;

    public FirebaseToken getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(FirebaseToken firebaseToken) {
        this.firebaseToken = firebaseToken;
    }
}
