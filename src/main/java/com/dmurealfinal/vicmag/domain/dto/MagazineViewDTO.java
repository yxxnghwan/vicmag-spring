package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.magazineview.MagazineView;
import lombok.Data;


@Data
public class MagazineViewDTO {
    private Long magazineViewSeq;

    private String userId;

    private Long magazineSeq;

    public MagazineView toEntity() {
        return MagazineView.builder()
                .userId(userId)
                .magazineSeq(magazineSeq)
                .build();
    }
}
