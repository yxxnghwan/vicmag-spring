package com.dmurealfinal.vicmag.domain.entity.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SinglePurchaseRepository extends JpaRepository<SinglePurchase, Long> {
    @Query("SELECT sp FROM SinglePurchase sp, Purchase p, Magazine m, MagazineBoard mb " +
            "WHERE sp.purchaseSeq = p.purchaseSeq " +
            "AND m.magazineSeq = sp.magazineSeq " +
            "AND m.board = mb " +
            "AND p.userId = :userId " +
            "AND mb.magazineBoardSeq = :boardSeq")
    List<SinglePurchase> findByUserAndBoard(@Param("userId") String userId, @Param("boardSeq") Long boardSeq);
}