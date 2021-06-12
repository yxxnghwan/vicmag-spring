package com.dmurealfinal.vicmag.service;

import com.dmurealfinal.vicmag.domain.dto.MagazineBoardDTO;
import com.dmurealfinal.vicmag.domain.dto.MagazineContentsDTO;
import com.dmurealfinal.vicmag.domain.dto.MagazineDTO;
import com.dmurealfinal.vicmag.domain.entity.account.CompanyRepository;
import com.dmurealfinal.vicmag.domain.entity.magazine.Magazine;
import com.dmurealfinal.vicmag.domain.entity.magazine.MagazineRepository;
import com.dmurealfinal.vicmag.domain.entity.magazineboard.MagazineBoard;
import com.dmurealfinal.vicmag.domain.entity.magazineboard.MagazineBoardRepository;
import com.dmurealfinal.vicmag.domain.entity.magazinecontents.MagazineContents;
import com.dmurealfinal.vicmag.domain.entity.magazinecontents.MagazineContentsRepository;
import com.dmurealfinal.vicmag.domain.entity.magazineview.MagazineViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MagazineService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    MagazineBoardRepository magazineBoardRepository;

    @Autowired
    MagazineRepository magazineRepository;

    @Autowired
    MagazineContentsRepository magazineContentsRepository;

    @Autowired
    MagazineViewRepository magazineViewRepository;

    @Transactional
    public void saveBoard(MagazineBoardDTO boardDTO) {
        MagazineBoard board = boardDTO.toEntity();
        magazineBoardRepository.save(board);
    }

    @Transactional
    public void saveMagazine(MagazineDTO magazineDTO) {
        Magazine magazine = magazineDTO.toEntity();
        magazineRepository.save(magazine);
    }

    @Transactional
    public void saveMagazineContents(MagazineContentsDTO magazineContentsDTO) {
        MagazineContents magazineContents = magazineContentsDTO.toEntity();
        magazineContentsRepository.save(magazineContents);
    }

}
