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
import java.util.List;

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

    @Transactional
    public List<MagazineBoardDTO> findMagazineBoardList() {
        List<MagazineBoard> magazineBoardList = magazineBoardRepository.findAll();
        return MagazineBoard.toDTOList(magazineBoardList);
    }

    @Transactional
    public MagazineBoardDTO findMagazineBoard(Long boardSeq) {
        MagazineBoard magazineBoard = magazineBoardRepository.getById(boardSeq);
        MagazineBoardDTO result = magazineBoard.toDTO();
        result.setCompany(magazineBoard.getCompany().toDTO());
        result.setMagazineList(Magazine.toDTOList(magazineRepository.findByBoardSeq(boardSeq)));
        return result;
    }

    @Transactional
    public MagazineDTO findMagazine(Long magazineSeq) {
        Magazine magazine = magazineRepository.getById(magazineSeq);
        MagazineDTO result = magazine.toDTO();
        result.setBoard(magazine.getBoard().toDTO());
        result.setMagazineContentsList(MagazineContents.toDTOList(magazineContentsRepository.findByMagazineSeq(magazineSeq)));
        return result;
    }

    @Transactional
    public MagazineContentsDTO findMagazineContents(Long contentsSeq) {
        MagazineContents magazineContents = magazineContentsRepository.getById(contentsSeq);
        MagazineContentsDTO result = magazineContents.toDTO();
        result.setMagazine(magazineContents.getMagazine().toDTO());
        return result;
    }

}
