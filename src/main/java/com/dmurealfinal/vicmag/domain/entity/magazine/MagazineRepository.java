package com.dmurealfinal.vicmag.domain.entity.magazine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MagazineRepository extends JpaRepository<Magazine, Long> {
    @Query("SELECT m FROM Magazine AS m WHERE m.board.magazineBoardSeq = :boardSeq")
    List<Magazine> findByBoardSeq(@Param("boardSeq") Long boardSeq);

    @Modifying
    @Query("UPDATE Magazine m SET " +
            "m.name = :name, " +
            "m.coverImgUrl = :coverImgUrl," +
            "m.price = :price," +
            "m.tag = :tag," +
            "m.bgmUrl = :bgmUrl " +
            "WHERE m.magazineSeq = :magazineSeq")
    int updateMagazine(@Param("magazineSeq") Long magazineSeq, @Param("name") String name, @Param("coverImgUrl") String coverImgUrl, @Param("price") Integer price, @Param("tag") String tag, @Param("bgmUrl") String bgmUrl);
}