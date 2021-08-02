package com.dmurealfinal.vicmag.controller;

import com.dmurealfinal.vicmag.domain.dto.AccountDTO;
import com.dmurealfinal.vicmag.domain.dto.CompanyDTO;
import com.dmurealfinal.vicmag.domain.dto.MagazineContentsDTO;
import com.dmurealfinal.vicmag.domain.dto.UserDTO;
import com.dmurealfinal.vicmag.domain.entity.account.*;
import com.dmurealfinal.vicmag.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @PostMapping("/login/user")
    public UserDTO loginUser(HttpServletRequest request, HttpServletResponse response, @RequestBody AccountDTO accountDTO) throws JsonProcessingException{
        logger.info("[loginUser 요청]");
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Account : " + objectMapper.writeValueAsString(accountDTO));
        UserDTO userDTO = accountService.findUser(accountDTO.getAccountId());
        if(userDTO != null) {
            // 계정 찾음
            if(BCrypt.checkpw(accountDTO.getPassword(), userDTO.getAccount().getPassword())) {

            } else {
                // 비밀번호 오류
            }
        } else {
            // 존재하지 않는 계정
        }
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
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("User : " + objectMapper.writeValueAsString(userDTO));
        accountService.updateUser(userDTO);
    }

    /** 잡지사 정보 수정 API */
    @PutMapping("/company")
    public void updateCompany(HttpServletRequest request, HttpServletResponse response, @RequestBody CompanyDTO companyDTO) throws JsonProcessingException {
        logger.info("[updateCompany 요청]");
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("User : " + objectMapper.writeValueAsString(companyDTO));
        accountService.updateCompany(companyDTO);
    }

    /** 사용자 삭제 API */
    @DeleteMapping("/user")
    public void deleteUser(HttpServletRequest request, HttpServletResponse response, @RequestBody UserDTO userDTO) throws JsonProcessingException {
        logger.info("[deleteUser 요청]");
        accountService.deleteUser(userDTO.getAccountId());
    }

    /** 잡지사 삭제 API */
    @DeleteMapping("/company")
    public void deleteCompany(HttpServletRequest request, HttpServletResponse response, @RequestBody CompanyDTO companyDTO) throws JsonProcessingException {
        logger.info("[deleteCompany 요청]");
        accountService.deleteCompany(companyDTO.getAccountId());
    }
}
