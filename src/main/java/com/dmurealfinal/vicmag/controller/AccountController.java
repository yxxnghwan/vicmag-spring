package com.dmurealfinal.vicmag.controller;

import com.dmurealfinal.vicmag.domain.dto.AccountDTO;
import com.dmurealfinal.vicmag.domain.dto.CompanyDTO;
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

}
