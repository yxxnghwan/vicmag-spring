package com.dmurealfinal.vicmag.domain.entity.faq;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface FaqRepository extends JpaRepository<Faq, Long> {

    @Modifying
    @Query("UPDATE Faq f SET " +
            "f.title = :title, " +
            "f.question = :question, " +
            "f.answer = :answer, " +
            "f.accountId = :accountId, " +
            "f.modifiedDateTime = :now " +
            "WHERE f.faqSeq = :faqSeq")
    int updateFaq(@Param("faqSeq") Long faqSeq, @Param("title") String title, @Param("question") String question, @Param("answer") String answer, @Param("accountId") String accountId, @Param("now") LocalDateTime now);
}