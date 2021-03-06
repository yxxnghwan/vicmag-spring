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
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AccountService accountService;

    /** 계정 리스트 */
    @GetMapping
    public List<AccountDTO> getAccounts(HttpServletRequest request, HttpServletResponse response) {
        logger.info("[getAccounts 요청]");
        return accountService.findAccounts();
    }

    /** 로그인 API */
    @PostMapping("/login")
    public LoginResponseDTO loginAccount(HttpServletRequest request, HttpServletResponse response, @RequestBody AccountDTO accountDTO) throws JsonProcessingException, IOException {
        logger.info("[loginAccount 요청]");
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Account : " + objectMapper.writeValueAsString(accountDTO));

        AccountDTO find = null;

        LoginResponseDTO result = new LoginResponseDTO();

        find = accountService.findAccountById(accountDTO.getAccountId());
        logger.info(objectMapper.writeValueAsString(find));

        if(find != null) {
            // 계정 찾음
            logger.info("계정 있음");
            if(BCrypt.checkpw(accountDTO.getPassword(), find.getPassword())) {
                // 비밀번호 정상
                logger.info("로그인 성공");
                result.setAccount(find);
                result.setJwt(JWTManager.createJWT(find));
            } else {
                // 비밀번호 오류
                logger.info("비밀번호 오류");
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "비밀번호 오류");
                result = null;
            }
        } else {
            // 존재하지 않는 계정
            logger.info("계정 없음");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "계정 없음");
            result = null;
        }

        return result;
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

    /** 관리자 추가 API */
    @PostMapping("/admin")
    public void postAdmin(HttpServletRequest request, HttpServletResponse response, @RequestBody AdminDTO adminDTO) throws JsonProcessingException, IOException {
        logger.info("[postUser 요청]");


        AccountDTO loginAccount = (AccountDTO)request.getAttribute("loginAccount");
        if(loginAccount == null) {
            logger.info("로그인 계정이 없습니다.");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "로그인 계정이 없습니다.");
            return;
        }

        if(!loginAccount.getAccountType().equals("admin")) {
            logger.info("관리자 계정만 관리자 추가 API를 요청할 수 있습니다.");
            response.sendError(HttpStatus.FORBIDDEN.value(), "관리자 계정만 관리자 추가 API를 요청할 수 있습니다.");
            return;
        }


        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Account : " + objectMapper.writeValueAsString(adminDTO));
        AccountDTO accountDTO = adminDTO.getAccount();
        accountDTO.setAccountType("admin");
        accountDTO.setAccountId(adminDTO.getAccountId());
        accountDTO.setPassword(BCrypt.hashpw(accountDTO.getPassword(), BCrypt.gensalt()));

        // 저장
        accountService.saveAdmin(accountDTO, adminDTO);
    }

    /** 사용자 추가 API */
    @PostMapping("/user")
    public void postUser(HttpServletRequest request, HttpServletResponse response, @RequestBody UserDTO userDTO) throws JsonProcessingException {
        logger.info("[postUser 요청]");
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("User : " + objectMapper.writeValueAsString(userDTO));
        AccountDTO accountDTO = userDTO.getAccount();
        accountDTO.setAccountType("user");
        accountDTO.setAccountId(userDTO.getAccountId());
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
        accountDTO.setAccountType("company");
        accountDTO.setAccountId(companyDTO.getAccountId());
        accountDTO.setPassword(BCrypt.hashpw(accountDTO.getPassword(), BCrypt.gensalt()));

        // 저장
        accountService.saveCompany(accountDTO, companyDTO);
    }

    /** 사용자 정보 수정 API */
    @PutMapping("/user")
    public void updateUser(HttpServletRequest request, HttpServletResponse response, @RequestBody UserDTO userDTO) throws JsonProcessingException, IOException {
        logger.info("[updateUser 요청]");

        AccountDTO loginAccount = (AccountDTO)request.getAttribute("loginAccount");

        if(loginAccount == null) {
            logger.info("로그인 계정이 없습니다.");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "로그인 계정이 없습니다.");
            return;
        }

        if(!loginAccount.getAccountType().equals("admin")) {
            if(!loginAccount.getAccountId().equals(userDTO.getAccountId())) {
                logger.info("수정하려는 계정정보로 로그인되어있지 않습니다.");
                response.sendError(HttpStatus.FORBIDDEN.value(), "수정하려는 계정정보로 로그인되어있지 않습니다.");
                return;
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("User : " + objectMapper.writeValueAsString(userDTO));
        accountService.updateUser(userDTO);
    }

    /** 잡지사 정보 수정 API */
    @PutMapping("/company")
    public void updateCompany(HttpServletRequest request, HttpServletResponse response, @RequestBody CompanyDTO companyDTO) throws JsonProcessingException, IOException {
        logger.info("[updateCompany 요청]");

        AccountDTO loginAccount = (AccountDTO) request.getAttribute("loginAccount");

        if(loginAccount == null) {
            logger.info("로그인 계정이 없습니다.");
            response.sendError(HttpStatus.UNAUTHORIZED.value(),"로그인 계정이 없습니다.");
            return;
        }

        if(!loginAccount.getAccountType().equals("admin")) {
            if(!loginAccount.getAccountId().equals(companyDTO.getAccountId())) {
                logger.info("수정하려는 계정정보로 로그인되어있지 않습니다.");
                response.sendError(HttpStatus.FORBIDDEN.value(), "수정하려는 계정정보로 로그인되어있지 않습니다.");
                return;
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("User : " + objectMapper.writeValueAsString(companyDTO));
        accountService.updateCompany(companyDTO);
    }

    /** 사용자 삭제 API */
    @DeleteMapping("/user")
    public void deleteUser(HttpServletRequest request, HttpServletResponse response, @RequestBody UserDTO userDTO) throws JsonProcessingException, IOException {
        logger.info("[deleteUser 요청]");

        AccountDTO loginAccount = (AccountDTO) request.getAttribute("loginAccount");

        if(loginAccount == null) {
            logger.info("로그인 계정이 없습니다.");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "로그인 계정이 없습니다.");
            return;
        }

        if(!loginAccount.getAccountType().equals("admin")) {
            if(!loginAccount.getAccountId().equals(userDTO.getAccountId())) {
                logger.info("삭제하려는 계정정보로 로그인되어있지 않습니다.");
                response.sendError(HttpStatus.FORBIDDEN.value(), "삭제하려는 계정정보로 로그인되어있지 않습니다.");
                return;
            }
        }

        accountService.deleteUser(userDTO.getAccountId());
    }

    /** 잡지사 삭제 API */
    @DeleteMapping("/company")
    public void deleteCompany(HttpServletRequest request, HttpServletResponse response, @RequestBody CompanyDTO companyDTO) throws JsonProcessingException, IOException {
        logger.info("[deleteCompany 요청]");

        AccountDTO loginAccount = (AccountDTO) request.getAttribute("loginAccount");

        if(loginAccount == null) {
            logger.info("로그인 계정이 없습니다.");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "로그인 계정이 없습니다.");
            return;
        }

        if(!loginAccount.getAccountType().equals("admin")) {
            if(!loginAccount.getAccountId().equals(companyDTO.getAccountId())) {
                logger.info("삭제하려는 계정정보로 로그인되어있지 않습니다.");
                response.sendError(HttpStatus.FORBIDDEN.value(), "삭제하려는 계정정보로 로그인되어있지 않습니다.");
                return;
            }
        }

        accountService.deleteCompany(companyDTO.getAccountId());
    }

    /** 비밀번호 변경 */
    @PatchMapping("/password")
    public void updatePassword(HttpServletRequest request, HttpServletResponse response, @RequestBody AccountDTO accountDTO) throws JsonProcessingException, IOException {
        logger.info("[updatePassword 요청]");

        AccountDTO loginAccount = (AccountDTO) request.getAttribute("loginAccount");

        if(loginAccount == null) {
            logger.info("로그인 계정이 없습니다.");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "로그인 계정이 없습니다.");
            return;
        }

        if(!loginAccount.getAccountType().equals("admin")) {
            if(!loginAccount.getAccountId().equals(accountDTO.getAccountId())) {
                logger.info("수정하려는 계정정보로 로그인되어있지 않습니다.");
                response.sendError(HttpStatus.FORBIDDEN.value(), "수정하려는 계정정보로 로그인되어있지 않습니다.");
                return;
            }
        }

        accountDTO.setPassword(BCrypt.hashpw(accountDTO.getPassword(), BCrypt.gensalt()));

        accountService.updatePassword(accountDTO);
    }
}
