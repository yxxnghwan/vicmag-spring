package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.magazine.Magazine;
import com.dmurealfinal.vicmag.domain.entity.magazineboard.MagazineBoard;
import lombok.Builder;
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

    public MagazineDTO() {}

    @Builder
    public MagazineDTO(Long magazineSeq, String name, String coverImgUrl, Integer price, String tag, String bgmUrl, MagazineBoardDTO board) {
        this.magazineSeq = magazineSeq;
        this.name = name;
        this.coverImgUrl = coverImgUrl;
        this.price = price;
        this.tag = tag;
        this.bgmUrl = bgmUrl;
        this.board = board;
    }

    public Magazine toEntity() {
        return Magazine.builder()
                .magazineSeq(this.magazineSeq)
                .name(this.name)
                .coverImgUrl(this.coverImgUrl)
                .price(this.price)
                .tag(this.tag)
                .bgmUrl(this.bgmUrl)
                .board((this.board == null ? null : this.board.toEntity()))
                .build();
    }
}
