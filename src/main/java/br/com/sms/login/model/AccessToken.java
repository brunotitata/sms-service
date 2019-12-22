package br.com.sms.login.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessToken {

    @JsonProperty("access_token")
    private String accessToken;

    public AccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @SuppressWarnings("unused")
    private AccessToken() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "AccessToken [accessToken=" + accessToken + "]";
    }

}
