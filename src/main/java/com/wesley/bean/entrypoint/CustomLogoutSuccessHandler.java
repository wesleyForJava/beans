package com.wesley.bean.entrypoint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler
		implements LogoutSuccessHandler {
	private static final String BEARER_AUTHENTICATION = "Bearer ";
	private static final String HEADER_AUTHORIZATION = "authorization";

	@Autowired
	private TokenStore toTokenStore;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String token = request.getHeader(BEARER_AUTHENTICATION);
		if (token != null && token.startsWith(HEADER_AUTHORIZATION)) {
			OAuth2AccessToken oAuth2AccessToken = toTokenStore.readAccessToken(token.split(" ")[0]);
			if (oAuth2AccessToken != null) {
				toTokenStore.removeAccessToken(oAuth2AccessToken);
			}
		}
		response.setStatus(HttpServletResponse.SC_OK);
	}
}
