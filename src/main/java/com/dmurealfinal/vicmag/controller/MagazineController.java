package com.dmurealfinal.vicmag.controller;

import com.dmurealfinal.vicmag.domain.dto.MagazineBoardDTO;
import com.dmurealfinal.vicmag.domain.dto.MagazineContentsDTO;
import com.dmurealfinal.vicmag.domain.dto.MagazineDTO;
import com.dmurealfinal.vicmag.domain.entity.magazine.Magazine;
import com.dmurealfinal.vicmag.service.MagazineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        logger.info("잡지 보드 리스트 요청");
        return magazineService.findMagazineBoardList();
    }

    /** 잡지 보드 상세 조회 */
    @GetMapping("/boards/{boardSeq}")
    public MagazineBoardDTO getMagazineBoard(HttpServletRequest request, HttpServletResponse response, @PathVariable Long boardSeq) {
        logger.info("잡지 보드 상세조회");
        return magazineService.findMagazineBoard(boardSeq);
    }

    /** 잡지 상세 조회 */
    @GetMapping("/magazines/{magazineSeq}")
    public MagazineDTO getMagazine(HttpServletRequest request, HttpServletResponse response, @PathVariable Long magazineSeq) {
        logger.info("잡지 상세 조회");
        return magazineService.findMagazine(magazineSeq);
    }

    /** 컨텐츠 상세 조회 */
    @GetMapping("/contents/{contentsSeq}")
    public MagazineContentsDTO getMagazineContents(HttpServletRequest request, HttpServletResponse response, @PathVariable Long contentsSeq) {
        logger.info("컨텐츠 상세 조회");
        return magazineService.findMagazineContents(contentsSeq);
    }




    /** 잡지 보드 등록 API */
    @PostMapping("/boards")
    public void postBoard(HttpServletRequest request, HttpServletResponse response, @RequestBody MagazineBoardDTO magazineBoardDTO) throws JsonProcessingException {
        logger.info("잡지 보드 등록 요청");
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("MagazineBoard : " + objectMapper.writeValueAsString(magazineBoardDTO));
        magazineService.saveBoard(magazineBoardDTO);
    }

    /** 잡지 등록 API */
    @PostMapping
    public void postMagazine(HttpServletRequest request, HttpServletResponse response, @RequestBody MagazineDTO magazineDTO) throws JsonProcessingException{
        logger.info("잡지 보드 등록 요청");
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("Magazine : " + objectMapper.writeValueAsString(magazineDTO));
        magazineService.saveMagazine(magazineDTO);
    }

    /** 잡지 컨텐츠 등록 API */
    @PostMapping("/contents")
    public void postMagazineContents(HttpServletRequest request, HttpServletResponse response, @RequestBody MagazineContentsDTO magazineContentsDTO) throws JsonProcessingException{
        logger.info("잡지 컨텐츠 등록 요청");
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("MagazineContents : " + objectMapper.writeValueAsString(magazineContentsDTO));

        magazineService.saveMagazineContents(magazineContentsDTO);
    }
}
