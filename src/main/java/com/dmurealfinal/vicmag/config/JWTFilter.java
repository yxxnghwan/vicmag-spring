package com.dmurealfinal.vicmag.config;

import com.dmurealfinal.vicmag.domain.dto.AccountDTO;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JWTFilter implements Filter {
    private final static Logger logger = LoggerFactory.getLogger(JWTFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        logger.info("Authorization 입력 필터링");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String jwt = request.getHeader("Authorization");

        if(jwt != null) {
            AccountDTO loginAccount = JWTManager.decodeJWT(jwt, response);
            if(loginAccount != null) {
                response.setHeader("Authorization", jwt);
                request.setAttribute("loginAccount", loginAccount);
            } else {
                response.setHeader("Authorization", null);
            }
        }

        chain.doFilter(request, response);
    }
}
