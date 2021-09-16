package com.dmurealfinal.vicmag.service;

import com.dmurealfinal.vicmag.domain.dto.NoticeDTO;
import com.dmurealfinal.vicmag.domain.entity.notice.Notice;
import com.dmurealfinal.vicmag.domain.entity.notice.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class NoticeService {
    @Autowired
    NoticeRepository noticeRepository;

    /** 공지사항 전체 조회 */
    public List<NoticeDTO> findNotices() {
        return Notice.toDTOList(noticeRepository.findAll());
    }

    /** seq로 공지사항 조회 */
    @Transactional
    public NoticeDTO findNoticeById(Long seq) {
        Notice notice = noticeRepository.findById(seq).get();
        if(notice == null) return null;

        return notice.toDTO();
    }

    /** 공지사항 쓰기 */
    @Transactional
    public void saveNotice(NoticeDTO noticeDTO) {
        noticeRepository.save(noticeDTO.toEntity());
    }

    /** 공지사항 수정 */
    @Transactional
    public void updateNotice(NoticeDTO noticeDTO) {
        noticeRepository.updateNotice(noticeDTO.getNoticeSeq(), noticeDTO.getTitle(), noticeDTO.getContents(), noticeDTO.getAccountId());
    }

    /** 공지사항 삭제 */
    @Transactional
    public void deleteNotice(Long noticeSeq) {
        noticeRepository.deleteById(noticeSeq);
    }

}
