package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.magazine.Magazine;
import com.dmurealfinal.vicmag.domain.entity.magazinecontents.MagazineContents;
import lombok.Data;

@Data
public class MagazineContentsDTO {
    private Long magazineContentsSeq;

    private Integer page;

    private String contentsType;

    private String contentsUrl;

    private String description;

    // for response
    private MagazineDTO magazine;

    public MagazineContents toEntity() {
        return MagazineContents.builder()
                .page(this.page)
                .contentsType(this.contentsType)
                .contentsUrl(this.contentsUrl)
                .description(this.description)
                .magazine(this.magazine.toEntity())
                .build();
    }
}
