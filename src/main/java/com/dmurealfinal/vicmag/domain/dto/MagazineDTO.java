package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.magazine.Magazine;
import com.dmurealfinal.vicmag.domain.entity.magazineboard.MagazineBoard;
import lombok.Data;

@Data
public class MagazineDTO {
    private Long magazineSeq;

    private String name;

    private String coverImgUrl;

    private Integer price;

    private String tag;

    private String bgmUrl;

    // for response
    private MagazineBoardDTO board;

    public Magazine toEntity() {
        return Magazine.builder()
                .name(this.name)
                .coverImgUrl(this.coverImgUrl)
                .price(this.price)
                .tag(this.tag)
                .bgmUrl(this.bgmUrl)
                .board(this.board.toEntity())
                .build();
    }
}
