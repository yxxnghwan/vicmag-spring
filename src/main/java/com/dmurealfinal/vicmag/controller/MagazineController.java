package com.dmurealfinal.vicmag.controller;

import com.dmurealfinal.vicmag.domain.dto.*;
import com.dmurealfinal.vicmag.domain.entity.magazine.Magazine;
import com.dmurealfinal.vicmag.service.MagazineService;
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
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/magazines")
public class MagazineController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MagazineService magazineService;

    /** 잡지 보드 리스트(메인) */
    @GetMapping("/boards")
    public List<MagazineBoardDTO> getMagazineBoardList(HttpServletRequest request, HttpServletResponse response) {
        logger.info("[getMagazineBoardList] 요청");
        return magazineService.findMagazineBoardList();
    }

    /** 잡지 보드 상세 조회 API */
    @GetMapping("/boards/{boardSeq}")
    public MagazineBoardDTO getMagazineBoard(HttpServletRequest request, HttpServletResponse response, @PathVariable Long boardSeq) {
        logger.info("[getMagazineBoard] 요청");
        return magazineService.findMagazineBoard(boardSeq);
    }

    /** 잡지 상세 조회 API */
    @GetMapping("/magazines/{magazineSeq}")
    public MagazineDTO getMagazine(HttpServletRequest request, HttpServletResponse response, @PathVariable Long magazineSeq) {
        logger.info("[getMagazine] 요청");
        return magazineService.findMagazine(magazineSeq);
    }

    /** 컨텐츠 상세 조회 API */
    @GetMapping("/contents/{contentsSeq}")
    public MagazineContentsDTO getMagazineContents(HttpServletRequest request, HttpServletResponse response, @PathVariable Long contentsSeq) {
        logger.info("[getMagazineContents] 요청");
        return magazineService.findMagazineContents(contentsSeq);
    }

    /** 잡지 보드 등록 API */
    @PostMapping("/boards")
    public void postBoard(HttpServletRequest request, HttpServletResponse response, @RequestBody MagazineBoardDTO magazineBoardDTO) throws JsonProcessingException, IOException {
        logger.info("[postBoard] 요청");

        AccountDTO loginAccount = (AccountDTO)request.getAttribute("loginAccount");

        if(loginAccount == null) {
            logger.info("로그인 계정이 없습니다.");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "로그인 계정이 없습니다.");
            return;
        }

        if(!loginAccount.getAccountType().equals("admin")) {
            if(!loginAccount.getAccountId().equals(magazineBoardDTO.getCompany().getAccountId())) {
                logger.info("등록하려는 잡지사계정으로 로그인되어있지 않습니다.");
                response.sendError(HttpStatus.FORBIDDEN.value(), "등록하려는 잡지사계정으로 로그인되어있지 않습니다.");
                return;
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("MagazineBoard : " + objectMapper.writeValueAsString(magazineBoardDTO));
        magazineService.saveBoard(magazineBoardDTO);
    }

    /** 잡지 등록 API */
    @PostMapping("/magazines")
    public void postMagazine(HttpServletRequest request, HttpServletResponse response, @RequestBody MagazineDTO magazineDTO) throws JsonProcessingException, IOException{
        logger.info("[postMagazine] 요청");

        AccountDTO loginAccount = (AccountDTO)request.getAttribute("loginAccount");

        if(loginAccount == null) {
            logger.info("로그인 계정이 없습니다.");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "로그인 계정이 없습니다.");
            return;
        }

        MagazineBoardDTO magazineBoardDTO = magazineService.findMagazineBoard(magazineDTO.getBoard().getMagazineBoardSeq());
        if(!loginAccount.getAccountType().equals("admin")) {
            if(!loginAccount.getAccountId().equals(magazineBoardDTO.getCompany().getAccountId())) {
                logger.info("등록하려는 잡지사계정으로 로그인되어있지 않습니다.");
                response.sendError(HttpStatus.FORBIDDEN.value(), "등록하려는 잡지사계정으로 로그인되어있지 않습니다.");
                return;
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Magazine : " + objectMapper.writeValueAsString(magazineDTO));
        magazineService.saveMagazine(magazineDTO);
    }

    /** 잡지 컨텐츠 등록 API */
    @PostMapping("/contents")
    public void postMagazineContents(HttpServletRequest request, HttpServletResponse response, @RequestBody MagazineContentsDTO magazineContentsDTO) throws JsonProcessingException, IOException{
        logger.info("[postMagazineContents] 요청");

        AccountDTO loginAccount = (AccountDTO)request.getAttribute("loginAccount");

        if(loginAccount == null) {
            logger.info("로그인 계정이 없습니다.");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "로그인 계정이 없습니다.");
            return;
        }

        MagazineDTO magazineDTO = magazineService.findMagazine(magazineContentsDTO.getMagazine().getMagazineSeq());
        if(!loginAccount.getAccountType().equals("admin")) {
            if(!loginAccount.getAccountId().equals(magazineDTO.getBoard().getCompany().getAccountId())) {
                logger.info("등록하려는 잡지사계정으로 로그인되어있지 않습니다.");
                response.sendError(HttpStatus.FORBIDDEN.value(), "등록하려는 잡지사계정으로 로그인되어있지 않습니다.");
                return;
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("MagazineContents : " + objectMapper.writeValueAsString(magazineContentsDTO));

        magazineService.saveMagazineContents(magazineContentsDTO);
    }

    /** 잡지 보드 수정 API */
    @PutMapping("/boards")
    public void updateBoard(HttpServletRequest request, HttpServletResponse response, @RequestBody MagazineBoardDTO magazineBoardDTO) throws JsonProcessingException, IOException {
        logger.info("[updateBoard] 요청");

        AccountDTO loginAccount = (AccountDTO)request.getAttribute("loginAccount");

        if(loginAccount == null) {
            logger.info("로그인 계정이 없습니다.");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "로그인 계정이 없습니다.");
            return;
        }

        MagazineBoardDTO oldObject = magazineService.findMagazineBoard(magazineBoardDTO.getMagazineBoardSeq());
        if(!loginAccount.getAccountType().equals("admin")) {
            if(!loginAccount.getAccountId().equals(oldObject.getCompany().getAccountId())) {
                logger.info("삭제하려는 잡지사계정으로 로그인되어있지 않습니다.");
                response.sendError(HttpStatus.FORBIDDEN.value(), "삭제하려는 잡지사계정으로 로그인되어있지 않습니다." );
                return;
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("MagazineBoard : " + objectMapper.writeValueAsString(magazineBoardDTO));

        magazineService.updateMagazineBoard(magazineBoardDTO);
    }

    /** 잡지 보드 삭제 API */
    @DeleteMapping("/boards")
    public void deleteBoard(HttpServletRequest request, HttpServletResponse response, @RequestBody MagazineBoardDTO magazineBoardDTO) throws JsonProcessingException, IOException {
        logger.info("[deleteBoard] 요청");

        AccountDTO loginAccount = (AccountDTO)request.getAttribute("loginAccount");

        if(loginAccount == null) {
            logger.info("로그인 계정이 없습니다.");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "로그인 계정이 없습니다.");
            return;
        }

        MagazineBoardDTO oldObject = magazineService.findMagazineBoard(magazineBoardDTO.getMagazineBoardSeq());
        if(!loginAccount.getAccountType().equals("admin")) {
            if(!loginAccount.getAccountId().equals(oldObject.getCompany().getAccountId())) {
                logger.info("삭제하려는 잡지사계정으로 로그인되어있지 않습니다.");
                response.sendError(HttpStatus.FORBIDDEN.value(), "삭제하려는 잡지사계정으로 로그인되어있지 않습니다." );
                return;
            }
        }

        magazineService.deleteMagazineBoard(magazineBoardDTO.getMagazineBoardSeq());
    }

    /** 잡지 수정 API */
    @PutMapping("/magazines")
    public void updateMagazine(HttpServletRequest request, HttpServletResponse response, @RequestBody MagazineDTO magazineDTO) throws JsonProcessingException, IOException {
        logger.info("[updateMagazine] 요청");

        AccountDTO loginAccount = (AccountDTO)request.getAttribute("loginAccount");

        if(loginAccount == null) {
            logger.info("로그인 계정이 없습니다.");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "로그인 계정이 없습니다.");
            return;
        }

        MagazineDTO oldObject = magazineService.findMagazine(magazineDTO.getMagazineSeq());
        if(!loginAccount.getAccountType().equals("admin")) {
            if(!loginAccount.getAccountId().equals(oldObject.getBoard().getCompany().getAccountId())) {
                logger.info("삭제하려는 잡지사계정으로 로그인되어있지 않습니다.");
                response.sendError(HttpStatus.FORBIDDEN.value(), "삭제하려는 잡지사계정으로 로그인되어있지 않습니다.");
                return;
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Magazine : " + objectMapper.writeValueAsString(magazineDTO));

        magazineService.updateMagazine(magazineDTO);
    }

    /** 잡지 삭제 API */
    @DeleteMapping("/magazines")
    public void deleteMagazine(HttpServletRequest request, HttpServletResponse response, @RequestBody MagazineDTO magazineDTO) throws JsonProcessingException, IOException {
        logger.info("[deleteMagazine] 요청");

        AccountDTO loginAccount = (AccountDTO)request.getAttribute("loginAccount");

        if(loginAccount == null) {
            logger.info("로그인 계정이 없습니다.");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "로그인 계정이 없습니다.");
            return;
        }

        MagazineDTO oldObject = magazineService.findMagazine(magazineDTO.getMagazineSeq());
        if(!loginAccount.getAccountType().equals("admin")) {
            if(!loginAccount.getAccountId().equals(oldObject.getBoard().getCompany().getAccountId())) {
                logger.info("삭제하려는 잡지사계정으로 로그인되어있지 않습니다.");
                response.sendError(HttpStatus.FORBIDDEN.value(), "삭제하려는 잡지사계정으로 로그인되어있지 않습니다.");
                return;
            }
        }

        logger.info("magazineSeq : " + magazineDTO.getMagazineSeq());

        magazineService.deleteMagazine(magazineDTO.getMagazineSeq());
    }

    /** 잡지 컨텐츠 수정 */
    @PutMapping("/contents")
    public void updateContents(HttpServletRequest request, HttpServletResponse response, @RequestBody MagazineContentsDTO magazineContentsDTO) throws JsonProcessingException, IOException {
        logger.info("[updateContents] 요청");

        AccountDTO loginAccount = (AccountDTO)request.getAttribute("loginAccount");

        if(loginAccount == null) {
            logger.info("로그인 계정이 없습니다.");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "로그인 계정이 없습니다.");
            return;
        }

        MagazineContentsDTO oldObject = magazineService.findMagazineContents(magazineContentsDTO.getMagazineContentsSeq());
        if(!loginAccount.getAccountType().equals("admin")) {
            if(!loginAccount.getAccountId().equals(oldObject.getMagazine().getBoard().getCompany().getAccountId())) {
                logger.info("수정하려는 잡지사계정으로 로그인되어있지 않습니다.");
                response.sendError(HttpStatus.FORBIDDEN.value(), "수정하려는 잡지사계정으로 로그인되어있지 않습니다.");
                return;
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("MagazineContents : " + objectMapper.writeValueAsString(magazineContentsDTO));

        magazineService.updateMagazineContents(magazineContentsDTO);
    }

    /** 잡지 컨텐츠 삭제 API */
    @DeleteMapping("/contents")
    public void deleteContents(HttpServletRequest request, HttpServletResponse response, @RequestBody MagazineContentsDTO magazineContentsDTO) throws JsonProcessingException, IOException {
        logger.info("[deleteContents] 요청");

        AccountDTO loginAccount = (AccountDTO)request.getAttribute("loginAccount");

        if(loginAccount == null) {
            logger.info("로그인 계정이 없습니다.");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "로그인 계정이 없습니다.");
            return;
        }

        MagazineDTO magazineDTO = magazineService.findMagazine(magazineContentsDTO.getMagazine().getMagazineSeq());
        if(!loginAccount.getAccountType().equals("admin")) {
            if(!loginAccount.getAccountId().equals(magazineDTO.getBoard().getCompany().getAccountId())) {
                logger.info("삭제하려는 잡지사계정으로 로그인되어있지 않습니다.");
                response.sendError(HttpStatus.FORBIDDEN.value(), "삭제하려는 잡지사계정으로 로그인되어있지 않습니다.");
                return;
            }
        }

        logger.info("magazineContentsSeq : " + magazineContentsDTO.getMagazineContentsSeq());
        magazineService.deleteMagazineContents(magazineContentsDTO.getMagazineContentsSeq());
    }

    /** 잡지 조회수 증가 API */
    @PostMapping("/view")
    public void viewMagazine(HttpServletRequest request, HttpServletResponse response, @RequestBody MagazineViewDTO magazineViewDTO) throws JsonProcessingException {
        logger.info("[viewMagazine] 요청");
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("MagazineView : " + objectMapper.writeValueAsString(magazineViewDTO));

        magazineService.saveMagazineView(magazineViewDTO);
    }

    /** 검색 API */
    @GetMapping("/search")
    public SearchResultDTO search(HttpServletRequest request, HttpServletResponse response, @RequestBody SearchDTO searchDTO) throws JsonProcessingException {
        logger.info("[search] 요청");
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("searchDTO : " + objectMapper.writeValueAsString(searchDTO));

        return magazineService.search(searchDTO);
    }
}
