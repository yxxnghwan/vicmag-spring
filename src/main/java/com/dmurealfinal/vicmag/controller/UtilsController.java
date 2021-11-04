package com.dmurealfinal.vicmag.controller;

import com.dmurealfinal.vicmag.service.UtilsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/utils")
public class UtilsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UtilsService utilsService;

    /** 카테고리 리스트 */
    @GetMapping("/category")
    List<String> getCategoryList(HttpServletRequest request, HttpServletResponse response) {
        logger.info("[getCategoryList] 요청");

        return utilsService.getCategoryList();
    }

}
