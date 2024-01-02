package com.rogeriogregorio.bookmanagementsystem.config;

import com.rogeriogregorio.bookmanagementsystem.repositories.UserRepository;
import com.rogeriogregorio.bookmanagementsystem.services.impl.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

            var token = this.recoverToken(request);

            if (token != null) {
                var login = tokenService.validateToken(token);
                UserDetails user = userRepository.findByLogin(login);

                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest httpServletRequest) {

        var authHeader = httpServletRequest.getHeader("Authorization");

        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }

    //httpResponse.addHeader("Access-Control-Allow-Origin", "*");
    //        httpResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    //        httpResponse.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
    //        httpResponse.addHeader("Access-Control-Allow-Credentials", "true");
}
