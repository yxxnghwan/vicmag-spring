package com.dmurealfinal.vicmag.controller;

import com.dmurealfinal.vicmag.config.JWTManager;
import com.dmurealfinal.vicmag.domain.dto.AccountDTO;
import com.dmurealfinal.vicmag.domain.dto.KakaoAccountDTO;
import com.dmurealfinal.vicmag.domain.dto.LoginResponseDTO;
import com.dmurealfinal.vicmag.domain.dto.kakao.Kakao_CodeDTO;
import com.dmurealfinal.vicmag.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/kakao")
public class KaKaoController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${kakaoRestApikey}")
    private String kakaoRestApiKey;
    @Value("${kakaoClientSecret}")
    private String kakaoClientSecret;

    @Autowired
    AccountService accountService;

    /** 카카오 계정 연결 - 토큰 저장 */
    @PostMapping("/connect")
    public Boolean receiveToken(HttpServletRequest request, HttpServletResponse response, @RequestBody Kakao_CodeDTO codeDTO) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("토큰 받기 : " + objectMapper.writeValueAsString(codeDTO));
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new FormHttpMessageConverter());
        converters.add(new StringHttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(converters);
        String tokenUrl = "https://kauth.kakao.com/oauth/token";

        MultiValueMap<String, String> tokenBody = new LinkedMultiValueMap<>();
        tokenBody.add("grant_type", "authorization_code");
        tokenBody.add("client_id", kakaoRestApiKey);
        String origin = request.getHeader("Origin");
        String redirect_uri = origin + "/oauth/kakao/connect";
        logger.info(redirect_uri);
        tokenBody.add("redirect_uri", redirect_uri);
        tokenBody.add("code", codeDTO.getCode());

        HttpEntity<MultiValueMap<String, String>> tokenEntity = new HttpEntity<>(tokenBody, headers);

        String tokenJsonData = restTemplate.postForObject(tokenUrl, tokenEntity, String.class);

        String token = objectMapper.readValue(tokenJsonData, new TypeReference<Map<String, Object>>() {}).get("access_token").toString();


        headers.add("Authorization", "Bearer " + token);

        String url = "https://kapi.kakao.com/v2/user/me";

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        String jsonData = restTemplate.postForObject(url, entity, String.class);

        logger.info(jsonData);

        Map<String, Object> map = objectMapper.readValue(jsonData, new TypeReference<Map<String, Object>>() {});
        logger.info("id : " + map.get("id"));
        logger.info("connected_at : " + map.get("connected_at").toString());
        Long kakaoIdNumber = Long.parseLong(map.get("id").toString());
        LocalDateTime connectedAt = LocalDateTime.parse(map.get("connected_at").toString().substring(0,19), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        logger.info("포멧된 kakaoIdNumber : " + kakaoIdNumber);
        logger.info("포멧된 데이트타임 : " + connectedAt);
        // id랑 connected_at 사용해서 DB 저장하기 + 응답하고 타임리프에서 알림 띄우기

        // 추후 WebFlux 사용 버전으로 변경 예정
//        WebClient webClient = WebClient.builder()
//                .defaultHeader("Authorization", "Bearer " + token.getAccess_token())
//                .build();
//
//        Mono<String> result = webClient.post()
//                .uri("https://kapi.kakao.com/v2/user/me")
//                .retrieve()
//                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
//                    logger.info("400 에러" + clientResponse);
//                    return null;
//                })
//                .bodyToMono(String.class);

        KakaoAccountDTO kakaoAccountDTO = KakaoAccountDTO.builder()
                .accountId(codeDTO.getAccountId())
                .kakaoIdNumber(kakaoIdNumber)
                .connectedAt(connectedAt)
                .build();

        return accountService.saveKakaoAccount(kakaoAccountDTO);
    }

    /** 카카오 로그인 - JWT 반환 */
    @PostMapping("/login")
    public LoginResponseDTO getJWT(HttpServletRequest request, HttpServletResponse response, @RequestBody Kakao_CodeDTO codeDTO) throws JsonProcessingException, HttpClientErrorException {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("토큰 받기 : " + objectMapper.writeValueAsString(codeDTO));
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new FormHttpMessageConverter());
        converters.add(new StringHttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(converters);
        String tokenUrl = "https://kauth.kakao.com/oauth/token";

        MultiValueMap<String, String> tokenBody = new LinkedMultiValueMap<>();
        tokenBody.add("grant_type", "authorization_code");
        tokenBody.add("client_id", kakaoRestApiKey);
        String origin = request.getHeader("Origin");
        String redirect_uri = origin + "/oauth/kakao/login";
        tokenBody.add("redirect_uri", redirect_uri);
        tokenBody.add("code", codeDTO.getCode());

        HttpEntity<MultiValueMap<String, String>> tokenEntity = new HttpEntity<>(tokenBody, headers);

        String tokenJsonData = restTemplate.postForObject(tokenUrl, tokenEntity, String.class);

        String token = objectMapper.readValue(tokenJsonData, new TypeReference<Map<String, Object>>() {}).get("access_token").toString();


        headers.add("Authorization", "Bearer " + token);

        String url = "https://kapi.kakao.com/v2/user/me";

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        String jsonData = restTemplate.postForObject(url, entity, String.class);

        logger.info(jsonData);

        Map<String, Object> map = objectMapper.readValue(jsonData, new TypeReference<Map<String, Object>>() {});
        logger.info("id : " + map.get("id"));
        logger.info("connected_at : " + map.get("connected_at").toString());
        Long kakaoIdNumber = Long.parseLong(map.get("id").toString());
        logger.info("포멧된 kakaoIdNumber : " + kakaoIdNumber);


        // 추후 WebFlux 사용 버전으로 변경 예정
//        WebClient webClient = WebClient.builder()
//                .defaultHeader("Authorization", "Bearer " + token.getAccess_token())
//                .build();
//
//        Mono<String> result = webClient.post()
//                .uri("https://kapi.kakao.com/v2/user/me")
//                .retrieve()
//                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
//                    logger.info("400 에러" + clientResponse);
//                    return null;
//                })
//                .bodyToMono(String.class);

        AccountDTO find = accountService.findAccountByKakaoIdNumber(kakaoIdNumber);
        LoginResponseDTO result = new LoginResponseDTO();
        if(find != null) {
            // 계정 찾음
            logger.info("카카오 로그인 성공");
            result.setAccount(find);
            result.setJwt(JWTManager.createJWT(find));
        } else {
            // 계정 찾기 실패
            logger.info("계정 없음");
            result = null;
        }

        return result;
    }

}
