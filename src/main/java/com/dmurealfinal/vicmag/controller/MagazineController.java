package com.dmurealfinal.vicmag.controller;

import com.dmurealfinal.vicmag.domain.entity.magazine.Magazine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/magazines")
public class MagazineController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping
    public List<Magazine> getMagazines() {
        logger.info("잡지 리스트 요청");
        List<Magazine> result = new ArrayList<Magazine>();
        Magazine testMagazine = new Magazine();
        result.add(testMagazine);

        return result;
    }
}
