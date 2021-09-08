package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.magazine.Magazine;
import com.dmurealfinal.vicmag.domain.entity.magazinecontents.MagazineContents;
import lombok.Builder;
import lombok.Data;

@Data
public class MagazineContentsDTO {
    private Long magazineContentsSeq;

    private Integer page;

    private String contentsType;

    private String contentsUrl;

    private String description;

    private String contentsThumbnailUrl;

    // for response
    private MagazineDTO magazine;

    public MagazineContentsDTO() {}

    @Builder
    public MagazineContentsDTO(Long magazineContentsSeq, Integer page, String contentsType, String contentsUrl, String description, String contentsThumbnailUrl, MagazineDTO magazine) {
        this.magazineContentsSeq = magazineContentsSeq;
        this.page = page;
        this.contentsType = contentsType;
        this.contentsUrl = contentsUrl;
        this.description = description;
        this.contentsThumbnailUrl = contentsThumbnailUrl;
        this.magazine = magazine;
    }

    public MagazineContents toEntity() {
        return MagazineContents.builder()
                .page(this.page)
                .contentsType(this.contentsType)
                .contentsUrl(this.contentsUrl)
                .description(this.description)
                .contentsThumbnailUrl(this.contentsThumbnailUrl)
                .magazine((this.magazine == null ? null : this.magazine.toEntity()))
                .build();
    }
}
