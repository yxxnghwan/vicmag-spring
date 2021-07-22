package com.dmurealfinal.vicmag.domain.entity.magazineview;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MagazineViewRepository extends JpaRepository<MagazineView, Long> {
    @Query("SELECT mv FROM MagazineView AS mv WHERE mv.userId = :userId AND mv.magazineSeq = :magazineSeq")
    MagazineView existsView(@Param("userId") String userId, @Param("magazineSeq") Long magazineSeq);
}