package br.com.app.myFinancy.config;


import br.com.app.myFinancy.service.TokenService;
import br.com.app.myFinancy.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class TokenAuthFilter extends OncePerRequestFilter {


    private UserService userService;

    private TokenService tokenService;


    public TokenAuthFilter(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);
        boolean isValid = tokenService.isValidToken(token);
        if(isValid) {
          injectSecurityUser(token);
            response.setHeader("access-control-expose-headers", "Authorization");
            response.setHeader("Authorization", "Bearer " + token);
        }
        filterChain.doFilter(request, response);

    }

    private void injectSecurityUser(String token) {
        UserDetails user = userService.loadUserByUsername(tokenService.findLoginUser(token));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null,
                user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private String recoverToken (HttpServletRequest request) {
        String token = request.getHeader("authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer")) {
            return null;
        }
        return token.substring(7, token.length());
    }
}
