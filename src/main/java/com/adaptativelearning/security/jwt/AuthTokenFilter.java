package com.adaptativelearning.security.jwt;

import static com.adaptativelearning.security.Constants.HEADER_AUTHORIZATION_KEY;
import static com.adaptativelearning.security.Constants.TOKEN_BEARER_PREFIX;

import com.adaptativelearning.security.services.UserDetailsServiceImpl;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthTokenFilter extends OncePerRequestFilter
{
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain)
    throws ServletException, IOException
    {
        try
        {
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt))
            {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource()
                    .buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        catch (Exception e)
        {
            logger.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request)
    {
        String headerAuth = request.getHeader(HEADER_AUTHORIZATION_KEY);

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(TOKEN_BEARER_PREFIX))
        {
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }
}