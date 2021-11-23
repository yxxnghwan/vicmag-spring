package com.dmurealfinal.vicmag.service;

import com.dmurealfinal.vicmag.domain.dto.FaqDTO;
import com.dmurealfinal.vicmag.domain.dto.NoticeDTO;
import com.dmurealfinal.vicmag.domain.entity.faq.Faq;
import com.dmurealfinal.vicmag.domain.entity.faq.FaqRepository;
import com.dmurealfinal.vicmag.domain.entity.notice.Notice;
import com.dmurealfinal.vicmag.domain.entity.notice.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FaqService {
    @Autowired
    FaqRepository faqRepository;

    /** FAQ 전체 조회 */
    public List<FaqDTO> findFaqs() {
        return Faq.toDTOList(faqRepository.findAll());
    }

    /** seq로 FAQ 조회 */
    @Transactional
    public FaqDTO findFaqById(Long seq) {
        Faq faq = faqRepository.findById(seq).get();
        if(faq == null) return null;

        return faq.toDTO();
    }

    /** FAQ 쓰기 */
    @Transactional
    public void saveFaq(FaqDTO faqDTO) {
        faqRepository.save(faqDTO.toEntity());
    }

    /** FAQ 수정 */
    @Transactional
    public void updateFaq(FaqDTO faqDTO) {
        faqRepository.updateFaq(faqDTO.getFaqSeq(), faqDTO.getTitle(), faqDTO.getQuestion(), faqDTO.getAnswer(), faqDTO.getAccountId(), LocalDateTime.now());
    }

    /** FAQ 삭제 */
    @Transactional
    public void deleteFaq(Long faqSeq) {
        faqRepository.deleteById(faqSeq);
    }
}
