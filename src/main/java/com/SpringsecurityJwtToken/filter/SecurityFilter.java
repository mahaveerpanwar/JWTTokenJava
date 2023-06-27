package com.SpringsecurityJwtToken.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.SpringsecurityJwtToken.util.JWTUtil;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private UserDetailsService detailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = request.getHeader("Authorization");

		if (token != null) {

			String getusername = jwtUtil.getusername(token);
			if (getusername != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails loadUserByUsername = detailsService.loadUserByUsername(getusername);
				boolean validateToken = jwtUtil.validateToken(token, loadUserByUsername.getUsername());
				if (validateToken) {
					UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(getusername,
							loadUserByUsername.getPassword(),loadUserByUsername.getAuthorities());
					
					authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authtoken);
				}
			}
		}
		filterChain.doFilter(request, response);
	}

}
