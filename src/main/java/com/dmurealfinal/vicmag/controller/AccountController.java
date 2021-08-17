package com.dmurealfinal.vicmag.controller;

import com.dmurealfinal.vicmag.config.JWTManager;
import com.dmurealfinal.vicmag.domain.dto.*;
import com.dmurealfinal.vicmag.domain.entity.account.*;
import com.dmurealfinal.vicmag.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AccountService accountService;

//    @GetMapping
//    public List<AccountDTO> getAccountList(HttpServletRequest request, HttpServletResponse response) {
//        return accountService.findAccounts().stream().map(dto -> dto.toDto());
//    }

    /** 로그인 API */
    @PostMapping("/login")
    public AccountDTO loginAccount(HttpServletRequest request, HttpServletResponse response, @RequestBody AccountDTO accountDTO) throws JsonProcessingException{
        logger.info("[loginAccount 요청]");
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Account : " + objectMapper.writeValueAsString(accountDTO));

        AccountDTO find = null;

        find = accountService.findAccountById(accountDTO.getAccountId());
        logger.info(objectMapper.writeValueAsString(find));

        if(find != null) {
            // 계정 찾음
            logger.info("계정 있음");
            if(BCrypt.checkpw(accountDTO.getPassword(), find.getPassword())) {
                // 비밀번호 정상
                logger.info("로그인 성공");
                response.setHeader("Authorization", JWTManager.createJWT(accountDTO));
            } else {
                // 비밀번호 오류
                logger.info("비밀번호 오류");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                find = null;
            }
        } else {
            // 존재하지 않는 계정
            logger.info("계정 없음");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            find = null;
        }
        return find;
    }

    /** 사용자 상세 조회 API */
    @GetMapping("/user/{userId}")
    public UserDTO getUser(HttpServletRequest request, HttpServletResponse response, @PathVariable String userId) {
        logger.info("[getUser 요청]");
        return accountService.findUser(userId);
    }

    /** 잡지사 상세 조회 API */
    @GetMapping("/company/{companyId}")
    public CompanyDTO getCompany(HttpServletRequest request, HttpServletResponse response, @PathVariable String companyId){
        logger.info("[getCompany 요청]");
        return accountService.findCompany(companyId);
    }

    /** 사용자 추가 API */
    @PostMapping("/user")
    public void postUser(HttpServletRequest request, HttpServletResponse response, @RequestBody UserDTO userDTO) throws JsonProcessingException {
        logger.info("[postUser 요청]");
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("User : " + objectMapper.writeValueAsString(userDTO));
        AccountDTO accountDTO = userDTO.getAccount();
        accountDTO.setPassword(BCrypt.hashpw(accountDTO.getPassword(), BCrypt.gensalt()));

        // 저장
        accountService.saveUser(accountDTO, userDTO);
    }

    /** 잡지사 추가 API */
    @PostMapping("/company")
    public void postCompany(HttpServletRequest request, HttpServletResponse response, @RequestBody CompanyDTO companyDTO) throws JsonProcessingException {
        logger.info("[postCompany 요청]");
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Company : " + objectMapper.writeValueAsString(companyDTO));
        AccountDTO accountDTO = companyDTO.getAccount();
        accountDTO.setPassword(BCrypt.hashpw(accountDTO.getPassword(), BCrypt.gensalt()));

        // 저장
        accountService.saveCompany(accountDTO, companyDTO);
    }

    /** 사용자 정보 수정 API */
    @PutMapping("/user")
    public void updateUser(HttpServletRequest request, HttpServletResponse response, @RequestBody UserDTO userDTO) throws JsonProcessingException {
        logger.info("[updateUser 요청]");

        AccountDTO loginAccount = (AccountDTO)request.getAttribute("loginAccount");

        if(loginAccount == null) {
            logger.info("로그인 계정이 없습니다.");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        if(!loginAccount.getAccountType().equals("admin")) {
            if(!loginAccount.getAccountId().equals(userDTO.getAccountId())) {
                logger.info("수정하려는 계정정보로 로그인되어있지 않습니다.");
                response.setStatus(HttpStatus.FORBIDDEN.value());
                return;
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("User : " + objectMapper.writeValueAsString(userDTO));
        accountService.updateUser(userDTO);
    }

    /** 잡지사 정보 수정 API */
    @PutMapping("/company")
    public void updateCompany(HttpServletRequest request, HttpServletResponse response, @RequestBody CompanyDTO companyDTO) throws JsonProcessingException {
        logger.info("[updateCompany 요청]");

        AccountDTO loginAccount = (AccountDTO) request.getAttribute("loginAccount");

        if(loginAccount == null) {
            logger.info("로그인 계정이 없습니다.");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        if(!loginAccount.getAccountType().equals("admin")) {
            if(!loginAccount.getAccountId().equals(companyDTO.getAccountId())) {
                logger.info("수정하려는 계정정보로 로그인되어있지 않습니다.");
                response.setStatus(HttpStatus.FORBIDDEN.value());
                return;
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("User : " + objectMapper.writeValueAsString(companyDTO));
        accountService.updateCompany(companyDTO);
    }

    /** 사용자 삭제 API */
    @DeleteMapping("/user")
    public void deleteUser(HttpServletRequest request, HttpServletResponse response, @RequestBody UserDTO userDTO) throws JsonProcessingException {
        logger.info("[deleteUser 요청]");

        AccountDTO loginAccount = (AccountDTO) request.getAttribute("loginAccount");

        if(loginAccount == null) {
            logger.info("로그인 계정이 없습니다.");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        if(!loginAccount.getAccountType().equals("admin")) {
            if(!loginAccount.getAccountId().equals(userDTO.getAccountId())) {
                logger.info("삭제하려는 계정정보로 로그인되어있지 않습니다.");
                response.setStatus(HttpStatus.FORBIDDEN.value());
                return;
            }
        }

        accountService.deleteUser(userDTO.getAccountId());
    }

    /** 잡지사 삭제 API */
    @DeleteMapping("/company")
    public void deleteCompany(HttpServletRequest request, HttpServletResponse response, @RequestBody CompanyDTO companyDTO) throws JsonProcessingException {
        logger.info("[deleteCompany 요청]");

        AccountDTO loginAccount = (AccountDTO) request.getAttribute("loginAccount");

        if(loginAccount == null) {
            logger.info("로그인 계정이 없습니다.");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        if(!loginAccount.getAccountType().equals("admin")) {
            if(!loginAccount.getAccountId().equals(companyDTO.getAccountId())) {
                logger.info("삭제하려는 계정정보로 로그인되어있지 않습니다.");
                response.setStatus(HttpStatus.FORBIDDEN.value());
                return;
            }
        }

        accountService.deleteCompany(companyDTO.getAccountId());
    }
}
