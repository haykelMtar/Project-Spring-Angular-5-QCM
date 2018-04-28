package org.exam.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		// response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Origin , Accept ,X-Requested-With , Content-Type ,"
				+ "Access-Control-Request-Method ," + "Access-Control-Request-Headers," + "Authorization");
		response.addHeader("Access-Control-Expose-Headers",
				"Access-Control-Allow-Origin," + "Access-Control-Allow-Credentials,Authorization");

		String jwt = request.getHeader(SecurityConstants.HEADER_STRING);
		System.out.println("============>" + jwt);

		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			return;
		} else {
			if (jwt == null || !jwt.startsWith(SecurityConstants.TOKEN_PREFIX)) {
				filterChain.doFilter(request, response);
				return;
			}
			Claims claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET)
					.parseClaimsJws(jwt.replace(SecurityConstants.TOKEN_PREFIX, "")).getBody();
			String username = claims.getSubject();

			ArrayList<Map<String, String>> roles = (ArrayList<Map<String, String>>) claims.get("roles");
			Collection<GrantedAuthority> authorities = new ArrayList<>();

			roles.forEach(r -> {
				authorities.add(new SimpleGrantedAuthority(r.get("authority")));
			});
			UsernamePasswordAuthenticationToken authenticationUser = new UsernamePasswordAuthenticationToken(username,
					null, authorities);
			SecurityContextHolder.getContext().setAuthentication(authenticationUser);

			filterChain.doFilter(request, response);

		}
	}

}
