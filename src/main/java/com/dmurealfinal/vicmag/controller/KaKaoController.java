package com.dmurealfinal.vicmag.controller;

import com.dmurealfinal.vicmag.domain.dto.KakaoAccountDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/kakao")
public class KaKaoController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /** 카카오 계정 연결 */
    @PostMapping("/connect")
    public void connectKakao(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {

    }
}
