package com.dmurealfinal.vicmag.domain.entity.contentsText;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContentsTextRepository extends JpaRepository<ContentsText, Long> {
    @Query("SELECT ct FROM ContentsText AS ct WHERE ct.magazineContents.magazineContentsSeq = :magazineContentsSeq ")
    List<ContentsText> findByMagazineContentsSeq(@Param("magazineContentsSeq") Long magazineContentsSeq);

    @Modifying
    @Query("UPDATE ContentsText ct SET " +
            "ct.startTime = :startTime, " +
            "ct.endTime = :endTime, " +
            "ct.text = :text " +
            "WHERE ct.contentsTextSeq = :contentsTextSeq")
    int updateContentsText(@Param("contentsTextSeq") Long contentsTextSeq, @Param("startTime") Integer startTime, @Param("endTime") Integer endTime, @Param("text") String text);
}