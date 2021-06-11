package com.dmurealfinal.vicmag.controller;

import com.dmurealfinal.vicmag.domain.dto.AccountDTO;
import com.dmurealfinal.vicmag.domain.dto.UserDTO;
import com.dmurealfinal.vicmag.domain.entity.account.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CompanyRepository companyRepository;

    /** 사용자 추가 API */
    @PostMapping("/user")
    public void postUser(HttpServletRequest request, HttpServletResponse response, @RequestBody UserDTO userDTO) throws JsonProcessingException {
        logger.info("[postUser 요청]");
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("User : " + objectMapper.writeValueAsString(userDTO));
        AccountDTO accountDTO = userDTO.getAccount();
        accountDTO.setPassword(BCrypt.hashpw(accountDTO.getPassword(), BCrypt.gensalt()));

        // Account 저장
        accountRepository.save(Account.builder()
                .accountId(accountDTO.getAccountId())
                .password(accountDTO.getPassword())
                .accountType(accountDTO.getAccountType())
                .build()
        );

        // User 저장
        userRepository.save(User.builder()
                .accountId(accountDTO.getAccountId())
                .name(userDTO.getName())
                .phone(userDTO.getPhone())
                .email(userDTO.getEmail())
                .build()
        );


    }


}
