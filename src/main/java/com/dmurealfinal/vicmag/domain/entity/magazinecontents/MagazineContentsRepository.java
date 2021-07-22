package com.dmurealfinal.vicmag.domain.entity.magazinecontents;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MagazineContentsRepository extends JpaRepository<MagazineContents, Long> {
    @Query("SELECT mc FROM MagazineContents AS mc WHERE mc.magazine.magazineSeq = :magazineSeq ORDER BY mc.page")
    List<MagazineContents> findByMagazineSeq(@Param("magazineSeq") Long magazineSeq);

    @Modifying
    @Query("UPDATE MagazineContents mc SET " +
            "mc.page = :page, " +
            "mc.contentsType = :contentsType, " +
            "mc.contentsUrl = :contentsUrl, " +
            "mc.description = :description " +
            "WHERE mc.magazineContentsSeq = :magazineContentsSeq")
    int updateMagazineContents(@Param("magazineContentsSeq") Long magazineContentsSeq, @Param("page") Integer page, @Param("contentsType") String contentsType, @Param("contentsUrl") String contentsUrl, @Param("description") String description);
}