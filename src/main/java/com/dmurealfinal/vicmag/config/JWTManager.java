package com.dmurealfinal.vicmag.config;

import com.dmurealfinal.vicmag.domain.dto.AccountDTO;
import com.dmurealfinal.vicmag.domain.entity.account.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JWTManager {
    private final static Logger logger = LoggerFactory.getLogger(JWTManager.class);
    @Value("${jwtkey}")
    private static String JWTkey;

    private static String headerName = "Authorization";

    @Value("${jwtkey}")
    public void setJWTkey(String jWTkey) {
        JWTkey = jWTkey;
    }

    // JWT 생성
    public static String createJWT(AccountDTO accountDTO) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");
        Map<String, Object> payloads = new HashMap<String, Object>();
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTimeInMillis(date.getTime());
        calendar.add(Calendar.MONTH, 6);
        date.setTime(calendar.getTimeInMillis());
        logger.info("입력만료시간" + date);
        payloads.put("exp", date);
        payloads.put("accountType", accountDTO.getAccountType());
        payloads.put("accountId",accountDTO.getAccountId());

        String jwt = Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .signWith(SignatureAlgorithm.HS256, JWTManager.JWTkey.getBytes())
                .compact();
        return jwt;
    }

    // JWT 디코딩
    public static AccountDTO decodeJWT(String jwt, HttpServletResponse response) {
        AccountDTO resultAccount = null;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(JWTManager.JWTkey.getBytes())
                    .parseClaimsJws(jwt)
                    .getBody();

            Date expiration = claims.get("exp", Date.class);
            expiration.setTime(expiration.getTime()/1000); // 이상하게 입력할때 밀리초를 초로 받음 그래서 나올땐 또 1000을 곱해서 나옴...
            Date now = new Date();
            resultAccount = new AccountDTO();
            resultAccount.setAccountId(claims.get("accountId", String.class));
            resultAccount.setAccountType(claims.get("accountType", String.class));

            // 기간 만료시 null 리턴
            if(expiration.getTime() < now.getTime()) {
                logger.info("jwt 기간 만료");
                resultAccount = null;
                response.setHeader(headerName, null);
            }
            // 기간이 얼마 남지 않았을 때
            else if(expiration.getTime()-1000*60*60*24*30 < now.getTime()) {
                logger.info("토큰 기간이 한달 이내로 남아 새 토큰 발급");
                String newJWT = JWTManager.createJWT(resultAccount);

                // 헤더에 넣기
                response.setHeader(JWTManager.headerName, newJWT);
                resultAccount = JWTManager.decodeJWT(newJWT, response);
            }
        } catch(JwtException e) {
            logger.info("토큰 변조 위험");
            resultAccount = null;						// null값으로 넘겨줘서 다시 로그인하도록
            response.setHeader(headerName, null);
        }

        // 정상 토큰 그냥 계정 리턴
        return resultAccount;
    }
}
