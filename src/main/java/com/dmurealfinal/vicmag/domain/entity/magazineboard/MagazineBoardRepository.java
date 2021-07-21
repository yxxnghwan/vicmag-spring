package com.dmurealfinal.vicmag.domain.entity.magazineboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MagazineBoardRepository extends JpaRepository<MagazineBoard, Long> {
    @Modifying
    @Query("UPDATE MagazineBoard b SET " +
            "b.name = :name, " +
            "b.boardImgUrl = :boardImgUrl, " +
            "b.description = :description, " +
            "b.pricePerMonth = :pricePerMonth, " +
            "b.category = :category " +
            "WHERE b.magazineBoardSeq = :magazineBoardSeq")
    int updateMagazineBoard(@Param("magazineBoardSeq") Long magazineBoardSeq, @Param("name") String name, @Param("boardImgUrl") String boardImgUrl, @Param("description") String description, @Param("pricePerMonth") Integer pricePerMonth, @Param("category") String category);
}