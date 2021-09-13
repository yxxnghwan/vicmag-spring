package com.dmurealfinal.vicmag.domain.entity.notice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Modifying
    @Query("UPDATE Notice n SET " +
            "n.title = :title, " +
            "n.contents = :contents " +
            "WHERE n.noticeSeq = :noticeSeq")
    int updateNotice(@Param("noticeSeq") Long noticeSeq, @Param("title") String title, @Param("contents") String contents);
}