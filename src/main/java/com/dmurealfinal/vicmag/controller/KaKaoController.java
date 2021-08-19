package com.dmurealfinal.vicmag.controller;

import com.dmurealfinal.vicmag.domain.dto.KakaoAccountDTO;
import com.dmurealfinal.vicmag.domain.dto.kakao.KakaoAuthCodeReceiveDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    /** 카카오 계정 연결 */
    @RequestMapping("/connect")
    public String connectKakao(Model model, @RequestBody KakaoAuthCodeReceiveDTO kakaoAuthCodeReceiveDTO) throws JsonProcessingException {

        return "kakao/kakao_connect";
    }
}
