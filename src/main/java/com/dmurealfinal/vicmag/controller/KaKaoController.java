package com.dmurealfinal.vicmag.controller;

import com.dmurealfinal.vicmag.domain.dto.kakao.KakaoAccessTokenDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/kakao")
public class KaKaoController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${kakaoRestApikey}")
    private String kakaoRestApiKey;
    @Value("${kakaoClientSecret}")
    private String kakaoClientSecret;
    @Value("${serverIP}")
    private String serverIP;
    /** 카카오 계정 연결 - 인가코드 받기 */
    @RequestMapping("/receive/code")
    public String receiveCode(Model model,
                              @RequestParam(name = "code", required = false) String code,
                              @RequestParam(name = "state", required = false) String state,
                              @RequestParam(name = "error", required = false) String error,
                              @RequestParam(name = "error_desctiprion", required = false) String error_description) throws JsonProcessingException {

        model.addAttribute("code", code);
        model.addAttribute("state", state);
        model.addAttribute("error", error);
        model.addAttribute("error_description", error_description);
        model.addAttribute("kakaoRestApiKey", kakaoRestApiKey);
        model.addAttribute("kakaoClientSecret", kakaoClientSecret);
        String redirectURI = "http://" + serverIP + ":10089/kakao/receive/code";
        model.addAttribute("redirectURI", redirectURI);
        return "kakao/kakao_connect";
    }

    /** 카카오 계정 연결 - 토큰 저장 */
    @PostMapping("/receive/token")
    public @ResponseBody Boolean receiveToken(@RequestBody KakaoAccessTokenDTO token) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("토큰 받기 : " + objectMapper.writeValueAsString(token));
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new FormHttpMessageConverter());
        converters.add(new StringHttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Bearer " + token.getAccess_token());
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(converters);
        String url = "https://kapi.kakao.com/v2/user/me";
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        String jsonData = restTemplate.postForObject(url, entity, String.class);

        logger.info(jsonData);



        return true;
    }

}
