package com.dmurealfinal.vicmag.domain.entity.magazine;

import com.dmurealfinal.vicmag.domain.entity.magazineboard.MagazineBoard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
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
            "m.bgmUrl = :bgmUrl ," +
            "m.modifiedDateTime = :now " +
            "WHERE m.magazineSeq = :magazineSeq")
    int updateMagazine(@Param("magazineSeq") Long magazineSeq, @Param("name") String name, @Param("coverImgUrl") String coverImgUrl, @Param("price") Integer price, @Param("tag") String tag, @Param("bgmUrl") String bgmUrl, @Param("now") LocalDateTime now);

    @Query("SELECT m FROM Magazine AS m , MagazineBoard AS mb " +
            "WHERE m.board = mb " +
            "AND mb.category LIKE %:category% " +
            "AND (m.name LIKE %:searchText% " +
            "OR m.tag LIKE %:searchText%) " +
            "ORDER BY m.magazineSeq DESC")
    List<Magazine> search(@Param("searchText") String searchText, @Param("category") String category , Pageable pageable);

    @Query("SELECT COUNT(m) FROM Magazine AS m , MagazineBoard AS mb " +
            "WHERE m.board = mb " +
            "AND mb.category LIKE %:category% " +
            "AND (m.name LIKE %:searchText% " +
            "OR m.tag LIKE %:searchText%) " +
            "ORDER BY m.magazineSeq DESC")
    Integer  pageCount(@Param("category") String category, @Param("searchText") String searchText);
}