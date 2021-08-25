package com.dmurealfinal.vicmag.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

}
