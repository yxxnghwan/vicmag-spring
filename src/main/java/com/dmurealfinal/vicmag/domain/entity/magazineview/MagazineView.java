package com.dmurealfinal.vicmag.domain.entity.magazineview;

import com.dmurealfinal.vicmag.domain.dto.MagazineContentsDTO;
import com.dmurealfinal.vicmag.domain.dto.MagazineViewDTO;
import com.dmurealfinal.vicmag.domain.entity.account.User;
import com.dmurealfinal.vicmag.domain.entity.magazine.Magazine;
import com.dmurealfinal.vicmag.domain.entity.magazinecontents.MagazineContents;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    public MagazineView(Long magazineViewSeq, String userId, Long magazineSeq) {
        this.magazineViewSeq = magazineViewSeq;
        this.userId = userId;
        this.magazineSeq = magazineSeq;
    }

    public MagazineViewDTO toDTO() {
        return MagazineViewDTO.builder()
                .userId(userId)
                .magazineSeq(magazineSeq)
                .build();
    }

    public static List<MagazineViewDTO> toDTOList (List<MagazineView> entityList) {
        List<MagazineViewDTO> result = new ArrayList<>();
        for(MagazineView entity : entityList) {
            result.add(entity.toDTO());
        }
        return result;
    }
}
