package com.klef.fsad.exam.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {
    	String path = request.getRequestURI();

    	// 🔥 SKIP AUTH FOR AUTH + SWAGGER
    	if (path.startsWith("/api/v1/auth") ||
    	    path.startsWith("/v3/api-docs") ||
    	    path.startsWith("/swagger-ui")) {

    	    chain.doFilter(request, response);
    	    return;
    	}

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);

            String email = jwtUtil.extractEmail(token);
            String role = jwtUtil.extractRole(token); // 🔥 GET ROLE

            var userDetails = service.loadUserByUsername(email);

            if (jwtUtil.validateToken(token, userDetails.getUsername())) {

                var authorities = List.of(
                        new SimpleGrantedAuthority("ROLE_" + role)
                );

                var auth = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        authorities
                );

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        chain.doFilter(request, response);
    }
}