package com.dmurealfinal.vicmag.controller;

import com.dmurealfinal.vicmag.domain.dto.AccountDTO;
import com.dmurealfinal.vicmag.domain.dto.NoticeDTO;
import com.dmurealfinal.vicmag.service.AccountService;
import com.dmurealfinal.vicmag.service.NoticeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/notices")
public class NoticeController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    NoticeService noticeService;

    /** 공지사항 전체 조회 */
    @GetMapping
    public List<NoticeDTO> getNotices(HttpServletRequest request, HttpServletResponse response) {
        logger.info("[getNotices 요청]");
        return noticeService.findNotices();
    }

    @GetMapping("/{noticeSeq}")
    public NoticeDTO getNotice(HttpServletRequest request, HttpServletResponse response, @PathVariable Long noticeSeq) {
        logger.info("[getNotice 요청]");
        return noticeService.findNoticeById(noticeSeq);
    }

    @PostMapping
    public void postNotice(HttpServletRequest request, HttpServletResponse response, @RequestBody NoticeDTO noticeDTO) throws JsonProcessingException, IOException {
        logger.info("[postNotice 요청]");

        AccountDTO loginAccount = (AccountDTO) request.getAttribute("loginAccount");

        if(loginAccount == null) {
            logger.info("로그인 계정이 없습니다.");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "로그인 계정이 없습니다.");
            return;
        }

        if(!loginAccount.getAccountType().equals("admin")) {
            logger.info("관리자만 공지사항을 등록할 수 있습니다.");
            response.sendError(HttpStatus.FORBIDDEN.value(), "관리자만 공지사항을 등록할 수 있습니다.");
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("User : " + objectMapper.writeValueAsString(noticeDTO));

        noticeService.saveNotice(noticeDTO);
    }

    @PutMapping
    public void updateNotice(HttpServletRequest request, HttpServletResponse response, @RequestBody NoticeDTO noticeDTO) throws JsonProcessingException, IOException {
        logger.info("[updatetice 요청]");

        AccountDTO loginAccount = (AccountDTO) request.getAttribute("loginAccount");

        if(loginAccount == null) {
            logger.info("로그인 계정이 없습니다.");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "로그인 계정이 없습니다.");
            return;
        }

        if(!loginAccount.getAccountType().equals("admin")) {
            logger.info("관리자만 공지사항을 수정할 수 있습니다.");
            response.sendError(HttpStatus.FORBIDDEN.value(), "관리자만 공지사항을 수정할 수 있습니다.");
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("User : " + objectMapper.writeValueAsString(noticeDTO));

        noticeService.updateNotice(noticeDTO);
    }

    @DeleteMapping
    public void deleteNotice(HttpServletRequest request, HttpServletResponse response, @RequestBody NoticeDTO noticeDTO) throws JsonProcessingException, IOException {
        logger.info("[deleteNotice 요청]");

        AccountDTO loginAccount = (AccountDTO) request.getAttribute("loginAccount");

        if(loginAccount == null) {
            logger.info("로그인 계정이 없습니다.");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "로그인 계정이 없습니다.");
            return;
        }

        if(!loginAccount.getAccountType().equals("admin")) {
            logger.info("관리자만 공지사항을 삭제할 수 있습니다.");
            response.sendError(HttpStatus.FORBIDDEN.value(), "관리자만 공지사항을 삭제할 수 있습니다.");
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("User : " + objectMapper.writeValueAsString(noticeDTO));

        noticeService.deleteNotice(noticeDTO.getNoticeSeq());
    }
}
