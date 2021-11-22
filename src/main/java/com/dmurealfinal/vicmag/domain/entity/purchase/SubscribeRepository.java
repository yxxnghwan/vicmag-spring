package com.dmurealfinal.vicmag.domain.entity.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    @Query("SELECT COUNT(s) FROM Subscribe s , Purchase p " +
            "WHERE s.purchaseSeq = p.purchaseSeq " +
            "AND p.userId = :userId " +
            "AND s.magazineBoardSeq = :boardSeq " +
            "AND (s.startDateTime <= :now AND s.endDateTime >= :now)")
    int findUserAndBoard(@Param("userId") String userId, @Param("boardSeq") Long boardSeq, @Param("now")LocalDateTime now);
}