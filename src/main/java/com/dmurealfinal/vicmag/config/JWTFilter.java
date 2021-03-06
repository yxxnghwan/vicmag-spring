package com.dmurealfinal.vicmag.config;

import com.dmurealfinal.vicmag.domain.dto.AccountDTO;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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

        String authorization = request.getHeader("Authorization");
        String jwt = null;

        if(authorization != null) {
            authorization = authorization.trim();
            String tokenType = authorization.split("\\s+")[0];
            if(!tokenType.equals("JWT")) {
                System.out.println("여기 걸림");
                response.sendError(HttpStatus.I_AM_A_TEAPOT.value(), "'JWT ~~~' 형식으로 JWT토큰을 넣어야합니다.");
                return;
            }

            jwt = authorization.split("\\s+")[1];
        }


        if(jwt != null) {
            AccountDTO loginAccount = JWTManager.decodeJWT(jwt, response);
            if(loginAccount != null) {
                response.setHeader("Authorization", jwt);
            } else {
                response.setHeader("Authorization", null);
            }
            request.setAttribute("loginAccount", loginAccount);
        }

        chain.doFilter(request, response);
    }
}
