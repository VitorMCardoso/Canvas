package br.com.fiap.mscanvas.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthentication extends OncePerRequestFilter {

    public JwtAuthentication(String... canPass) {
    }

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars"
    };

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        if (processWhiteList(AUTH_WHITELIST, request)) {
            chain.doFilter(request, response);
        } else {
            log.debug("Unauthorized: ", request);
        }
    }

    private boolean processWhiteList(String[] arrayWl, HttpServletRequest request) {
        return StringUtils.startsWithAny(request.getServletPath(), arrayWl);
    }
}
