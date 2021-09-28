package com.example.demo.filter;


import com.example.demo.services.JwtService;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Component
@Order(1)
public class AuthorizationFilter implements Filter {

    private final JwtService jwtService;

    public AuthorizationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        System.out.println(httpRequest.getClass().getCanonicalName());

        var jwt = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);

        System.out.println("JWT: [" + jwt + "]");

        if (jwt != null) {

            var claims = jwtService.decode(jwt);

            var auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), jwt, Set.of(
                    new SimpleGrantedAuthority("ROLE_USER"),
                    new SimpleGrantedAuthority("ROLE_ADMIN")
            ));

            auth.setDetails(Map.of("A", "B"));

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        chain.doFilter(request, response);
    }
}
