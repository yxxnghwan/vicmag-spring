package com.dmurealfinal.vicmag.domain.entity.magazine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MagazineRepository extends JpaRepository<Magazine, Long> {
    @Query("SELECT m FROM Magazine AS m WHERE m.board.magazineBoardSeq = :boardSeq")
    List<Magazine> findByBoardSeq(@Param("boardSeq") Long boardSeq);
}