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

    /** 잡지 보드 추가 */
    @Transactional
    public void saveBoard(MagazineBoardDTO boardDTO) {
        MagazineBoard board = boardDTO.toEntity();
        magazineBoardRepository.save(board);
    }

    /** 잡지 추가 */
    @Transactional
    public void saveMagazine(MagazineDTO magazineDTO) {
        Magazine magazine = magazineDTO.toEntity();
        magazineRepository.save(magazine);
    }

    /** 잡지 컨텐츠 추가 */
    @Transactional
    public void saveMagazineContents(MagazineContentsDTO magazineContentsDTO) {
        MagazineContents magazineContents = magazineContentsDTO.toEntity();
        magazineContentsRepository.save(magazineContents);
    }

    /** 잡지 보드 리스트 조회 */
    @Transactional
    public List<MagazineBoardDTO> findMagazineBoardList() {
        List<MagazineBoard> magazineBoardList = magazineBoardRepository.findAll();

        if(magazineBoardList == null) return null;

        return MagazineBoard.toDTOList(magazineBoardList);
    }

    /** 잡지 보드 상세조회 */
    @Transactional
    public MagazineBoardDTO findMagazineBoard(Long boardSeq) {
        MagazineBoard magazineBoard = magazineBoardRepository.getById(boardSeq);

        if(magazineBoard == null) return null;

        MagazineBoardDTO result = magazineBoard.toDTO();
        result.setCompany(magazineBoard.getCompany().toDTO());
        result.setMagazineList(Magazine.toDTOList(magazineRepository.findByBoardSeq(boardSeq)));
        return result;
    }

    /** 잡지 상세 조회 */
    @Transactional
    public MagazineDTO findMagazine(Long magazineSeq) {
        Magazine magazine = magazineRepository.getById(magazineSeq);

        if(magazine == null) return null;

        MagazineDTO result = magazine.toDTO();
        result.setBoard(magazine.getBoard().toDTO());
        result.setMagazineContentsList(MagazineContents.toDTOList(magazineContentsRepository.findByMagazineSeq(magazineSeq)));
        return result;
    }

    /** 잡지 컨텐츠 조회 */
    @Transactional
    public MagazineContentsDTO findMagazineContents(Long contentsSeq) {
        MagazineContents magazineContents = magazineContentsRepository.getById(contentsSeq);

        if(magazineContents == null) return null;

        MagazineContentsDTO result = magazineContents.toDTO();
        result.setMagazine(magazineContents.getMagazine().toDTO());
        return result;
    }

    /** 잡지 보드 수정 */
    @Transactional
    public void updateMagazineBoard(MagazineBoardDTO magazineBoardDTO) {
        magazineBoardRepository.updateMagazineBoard(magazineBoardDTO.getMagazineBoardSeq(), magazineBoardDTO.getName(), magazineBoardDTO.getBoardImgUrl(), magazineBoardDTO.getDescription(), magazineBoardDTO.getPricePerMonth(), magazineBoardDTO.getCategory());
    }

    /** 잡지 보드 삭제 */
    @Transactional
    public void deleteMagazineBoard(Long magazineBoardSeq) {
        magazineBoardRepository.deleteById(magazineBoardSeq);
    }
}
