package com.dmurealfinal.vicmag.controller;

import com.dmurealfinal.vicmag.domain.dto.MagazineBoardDTO;
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

    @GetMapping
    public List<Magazine> getMagazines() {
        logger.info("잡지 리스트 요청");
        List<Magazine> result = new ArrayList<Magazine>();
        Magazine testMagazine = new Magazine();
        result.add(testMagazine);

        return result;
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

    /**  */
}
