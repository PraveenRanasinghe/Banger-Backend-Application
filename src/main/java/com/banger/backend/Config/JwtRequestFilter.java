package com.banger.backend.Config;

import com.banger.backend.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private userService jwtUserDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            filterChain.doFilter(request, response);
            return;
        } else {
            try {
                String token = authHeader.substring("Bearer ".length());
                String usernameFromToken = jwtTokenUtil.getUsernameFromToken(token);
                UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(usernameFromToken);
                Boolean isTokenValid = jwtTokenUtil.validateToken(token, userDetails);

                if (isTokenValid && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken uPToken = new UsernamePasswordAuthenticationToken(
                            userDetails.getUsername(), null, userDetails.getAuthorities()
                    );
                    uPToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(uPToken);
                } else {
                    SecurityContextHolder.getContext().setAuthentication(null);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                filterChain.doFilter(request, response);
            }
        }
    }
}
