package com.dmurealfinal.vicmag.domain.entity.magazineview;

import com.dmurealfinal.vicmag.domain.entity.account.User;
import com.dmurealfinal.vicmag.domain.entity.magazine.Magazine;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Table(name = "TB_MAGAZINE_VIEW")
@Entity
public class MagazineView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long magazineViewSeq;

    @Column
    private String userId;

    @Column
    private Long magazineSeq;

    public MagazineView() {}

    @Builder
    public MagazineView(String userId, Long magazineSeq) {
        this.userId = userId;
        this.magazineSeq = magazineSeq;
    }
}
