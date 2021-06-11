package com.dmurealfinal.vicmag.domain.dto;

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

    private MagazineBoard board;
}
