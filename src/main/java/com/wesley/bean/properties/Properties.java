package com.wesley.bean.properties;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
@Component
@ConfigurationProperties(prefix="authentication.oauth")
@Validated
public class Properties {
	@NotNull
	private String clientid;
	private String secret;
	private String tokenValidityInSeconds;
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getTokenValidityInSeconds() {
		return tokenValidityInSeconds;
	}
	public void setTokenValidityInSeconds(String tokenValidityInSeconds) {
		this.tokenValidityInSeconds = tokenValidityInSeconds;
	}
	@Override
	public String toString() {
		return "Properties [clientid=" + clientid + ", secret=" + secret + ", tokenValidityInSeconds="
				+ tokenValidityInSeconds + "]";
	}
	
}
