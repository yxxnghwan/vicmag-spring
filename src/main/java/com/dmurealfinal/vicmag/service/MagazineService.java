package com.dmurealfinal.vicmag.service;

import com.dmurealfinal.vicmag.domain.dto.*;
import com.dmurealfinal.vicmag.domain.entity.account.CompanyRepository;
import com.dmurealfinal.vicmag.domain.entity.contentsText.ContentsText;
import com.dmurealfinal.vicmag.domain.entity.contentsText.ContentsTextRepository;
import com.dmurealfinal.vicmag.domain.entity.magazine.Magazine;
import com.dmurealfinal.vicmag.domain.entity.magazine.MagazineRepository;
import com.dmurealfinal.vicmag.domain.entity.magazineboard.MagazineBoard;
import com.dmurealfinal.vicmag.domain.entity.magazineboard.MagazineBoardRepository;
import com.dmurealfinal.vicmag.domain.entity.magazinecontents.MagazineContents;
import com.dmurealfinal.vicmag.domain.entity.magazinecontents.MagazineContentsRepository;
import com.dmurealfinal.vicmag.domain.entity.magazineview.MagazineView;
import com.dmurealfinal.vicmag.domain.entity.magazineview.MagazineViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
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

    @Autowired
    ContentsTextRepository contentsTextRepository;

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
        result.getBoard().setCompany(magazine.getBoard().getCompany().toDTO());
        result.getBoard().setMagazineList(Magazine.toDTOList(magazineRepository.findByBoardSeq(magazine.getBoard().getMagazineBoardSeq())));
        return result;
    }

    /** 잡지 컨텐츠 조회 */
    @Transactional
    public MagazineContentsDTO findMagazineContents(Long contentsSeq) {
        MagazineContents magazineContents = magazineContentsRepository.getById(contentsSeq);

        if(magazineContents == null) return null;

        MagazineContentsDTO result = magazineContents.toDTO();
        result.setMagazine(magazineContents.getMagazine().toDTO());
        result.getMagazine().setBoard(magazineContents.getMagazine().getBoard().toDTO());
        result.getMagazine().getBoard().setCompany(magazineContents.getMagazine().getBoard().getCompany().toDTO());
        result.getMagazine().setMagazineContentsList(MagazineContents.toDTOList(magazineContentsRepository.findByMagazineSeq(magazineContents.getMagazine().getMagazineSeq())));
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

    /** 잡지 수정 */
    @Transactional
    public void updateMagazine(MagazineDTO magazineDTO) {
        magazineRepository.updateMagazine(magazineDTO.getMagazineSeq(), magazineDTO.getName(), magazineDTO.getCoverImgUrl(), magazineDTO.getPrice(), magazineDTO.getTag(), magazineDTO.getBgmUrl(), LocalDateTime.now());
    }

    /** 잡지 삭제 */
    @Transactional
    public void deleteMagazine(Long magazineSeq) {
        magazineRepository.deleteById(magazineSeq);
    }

    /** 컨텐츠 수정 */
    @Transactional
    public void updateMagazineContents(MagazineContentsDTO magazineContentsDTO) {
        magazineContentsRepository.updateMagazineContents(magazineContentsDTO.getMagazineContentsSeq(), magazineContentsDTO.getPage(), magazineContentsDTO.getContentsType(), magazineContentsDTO.getContentsUrl(), magazineContentsDTO.getDescription(), magazineContentsDTO.getUploadStatus());
    }

    /** 컨텐츠 삭제 */
    @Transactional
    public void deleteMagazineContents(Long magazineContentsSeq) {
        magazineContentsRepository.deleteById(magazineContentsSeq);
    }

    /** 잡지 조회수 증가 */
    @Transactional
    public void saveMagazineView(MagazineViewDTO magazineViewDTO) {
        if(magazineViewRepository.existsView(magazineViewDTO.getUserId(), magazineViewDTO.getMagazineSeq()) == null) {
            MagazineView magazineView = magazineViewDTO.toEntity();
            magazineViewRepository.save(magazineView);
        }
    }

    /** 검색 */
    @Transactional
    public SearchResultDTO search(SearchDTO searchDTO) {
        SearchResultDTO result = new SearchResultDTO();

        Pageable boardPageable = PageRequest.of(searchDTO.getBoardPage() - 1, searchDTO.getBoardSize());
        List<MagazineBoard> magazineBoardList = magazineBoardRepository.search(searchDTO.getSearchText(), searchDTO.getCategory(), boardPageable);
        result.setMagazineBoards(MagazineBoard.toDTOList(magazineBoardList));

        Pageable magazinePageable = PageRequest.of(searchDTO.getMagazinePage() - 1, searchDTO.getMagazineSize());
        List<Magazine> magazineList = magazineRepository.search(searchDTO.getSearchText(), searchDTO.getCategory(), magazinePageable);
        result.setMagazines(Magazine.toDTOList(magazineList));

        return result;
    }

    /** 컨텐츠 텍스트 조회 */
    @Transactional
    public List<ContentsTextDTO> findContentsText(Long magazineContentsSeq) {
        return ContentsText.toDTOList(contentsTextRepository.findByMagazineContentsSeq(magazineContentsSeq));
    }

    /** 컨텐츠 텍스트 등록 */
    public void saveContentsText(ContentsTextDTO contentsTextDTO) {
        ContentsText contentsText = contentsTextDTO.toEntity();
        contentsTextRepository.save(contentsText);
    }
}
