package com.dmurealfinal.vicmag.domain.entity.purchase;

import com.dmurealfinal.vicmag.domain.entity.magazine.Magazine;
import com.dmurealfinal.vicmag.domain.entity.magazineboard.MagazineBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @Query("SELECT COUNT(p) FROM Purchase AS p, SinglePurchase AS sp " +
            "WHERE p.purchaseSeq = sp.purchaseSeq " +
            "AND p.userId = :userId " +
            "AND sp.magazineSeq = :magazineSeq")
    Integer isSinglePurchased(@Param("userId") String userId, @Param("magazineSeq") Long magazineSeq);

    @Query("SELECT COUNT(p) FROM Purchase AS p, Subscribe AS s " +
            "WHERE p.purchaseSeq = s.purchaseSeq " +
            "AND p.userId = :userId " +
            "AND s.magazineBoardSeq = :magazineBoardSeq " +
            "AND ((s.startDateTime >= :startDateTime AND s.startDateTime <= :endDateTime) OR (s.endDateTime >= :startDateTime AND s.endDateTime <= :endDateTime))")
    Integer isSubscribed(@Param("userId") String userId, @Param("magazineBoardSeq") Long magazineBoardSeq, @Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);

    @Query("SELECT m FROM Purchase p, SinglePurchase sp, Magazine m " +
            "WHERE p.purchaseSeq = sp.purchaseSeq " +
            "AND sp.magazineSeq = m.magazineSeq " +
            "AND p.userId = :userId")
    List<Magazine> getMyPurchaseMagazines(@Param("userId") String userId);

    @Query("SELECT mb FROM Purchase p, Subscribe s, MagazineBoard mb " +
            "WHERE p.purchaseSeq = s.purchaseSeq " +
            "AND s.magazineBoardSeq = mb.magazineBoardSeq " +
            "AND p.userId = :userId")
    List<MagazineBoard> getMyPurchaseBoards(@Param("userId") String userId);
}