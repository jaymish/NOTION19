package com.notion.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// set our response to OK status
		response.setStatus(HttpServletResponse.SC_OK);

		boolean admin = false;
		boolean user = false;
		boolean collector = false;
		
		System.out.println("AT onAuthenticationSuccess(...) function!");

		for (GrantedAuthority auth : authentication.getAuthorities()) {
			if ("ROLE_ADMIN".equals(auth.getAuthority())) {
				admin = true;
			}else if ("ROLE_USER".equals(auth.getAuthority())) {
				user = true;
			}
			else if ("ROLE_COLLECTOR".equals(auth.getAuthority())) {
				collector = true;
			}else {
				
			}
		}

		if (admin) {
			System.out.println("user is admin");
			response.sendRedirect("/admin/Dashboard");
		} else if(user) {
			System.out.println("user is user");
			response.sendRedirect("/user/Dashboard");
		} else if(collector) {
			System.out.println("user is collector");
			response.sendRedirect("/admin/collector");
		}else {
			System.out.println("user is anonymous");
			response.sendRedirect("/403");
		}
	}
}
