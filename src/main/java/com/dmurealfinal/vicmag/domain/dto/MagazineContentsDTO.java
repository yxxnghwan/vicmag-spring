package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.magazine.Magazine;
import lombok.Data;

@Data
public class MagazineContentsDTO {
    private Long magazineContentsSeq;

    private Integer page;

    private String contentsType;

    private String contentsUrl;

    private String description;

    private Magazine magazine;
}
