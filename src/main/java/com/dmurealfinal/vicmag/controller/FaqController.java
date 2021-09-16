package com.dmurealfinal.vicmag.controller;

import com.dmurealfinal.vicmag.domain.dto.AccountDTO;
import com.dmurealfinal.vicmag.domain.dto.FaqDTO;
import com.dmurealfinal.vicmag.domain.dto.NoticeDTO;
import com.dmurealfinal.vicmag.service.FaqService;
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
@RequestMapping("/api/faqs")
public class FaqController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FaqService faqService;

    /** FAQ 전체 조회 */
    @GetMapping
    public List<FaqDTO> getFaqs(HttpServletRequest request, HttpServletResponse response) {
        logger.info("[getFaqs 요청]");
        return faqService.findFaqs();
    }

    /** FAQ 상세 조회 */
    @GetMapping("/{faqSeq}")
    public FaqDTO getFaq(HttpServletRequest request, HttpServletResponse response, @PathVariable Long faqSeq) {
        logger.info("[getFaq 요청]");
        return faqService.findFaqById(faqSeq);
    }

    /** FAQ 등록 */
    @PostMapping
    public void postFaq(HttpServletRequest request, HttpServletResponse response, @RequestBody FaqDTO faqDTO) throws JsonProcessingException, IOException {
        logger.info("[postFaq 요청]");

        AccountDTO loginAccount = (AccountDTO) request.getAttribute("loginAccount");

        if(loginAccount == null) {
            logger.info("로그인 계정이 없습니다.");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "로그인 계정이 없습니다.");
            return;
        }

        if(!loginAccount.getAccountType().equals("admin")) {
            logger.info("관리자만 FAQ를 등록할 수 있습니다.");
            response.sendError(HttpStatus.FORBIDDEN.value(), "관리자만 공지사항을 등록할 수 있습니다.");
            return;
        }

        faqDTO.setAccountId(loginAccount.getAccountId());

        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("FAQ : " + objectMapper.writeValueAsString(faqDTO));

        faqService.saveFaq(faqDTO);
    }


    /** FAQ 수정 */
    @PutMapping
    public void updateFaq(HttpServletRequest request, HttpServletResponse response, @RequestBody FaqDTO faqDTO) throws JsonProcessingException, IOException {
        logger.info("[updateFaq 요청]");

        AccountDTO loginAccount = (AccountDTO) request.getAttribute("loginAccount");

        if(loginAccount == null) {
            logger.info("로그인 계정이 없습니다.");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "로그인 계정이 없습니다.");
            return;
        }

        if(!loginAccount.getAccountType().equals("admin")) {
            logger.info("관리자만 FAQ를 수정할 수 있습니다.");
            response.sendError(HttpStatus.FORBIDDEN.value(), "관리자만 FAQ를 수정할 수 있습니다.");
            return;
        }

        faqDTO.setAccountId(loginAccount.getAccountId());

        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("FAQ : " + objectMapper.writeValueAsString(faqDTO));

        faqService.updateFaq(faqDTO);
    }

    /** FAQ 삭제 */
    @DeleteMapping
    public void deleteFaq(HttpServletRequest request, HttpServletResponse response, @RequestBody FaqDTO faqDTO) throws JsonProcessingException, IOException {
        logger.info("[deleteNotice 요청]");

        AccountDTO loginAccount = (AccountDTO) request.getAttribute("loginAccount");

        if(loginAccount == null) {
            logger.info("로그인 계정이 없습니다.");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "로그인 계정이 없습니다.");
            return;
        }

        if(!loginAccount.getAccountType().equals("admin")) {
            logger.info("관리자만 FAQ를 삭제할 수 있습니다.");
            response.sendError(HttpStatus.FORBIDDEN.value(), "관리자만 FAQ를 삭제할 수 있습니다.");
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("FAQ : " + objectMapper.writeValueAsString(faqDTO));

        faqService.deleteFaq(faqDTO.getFaqSeq());
    }
}
