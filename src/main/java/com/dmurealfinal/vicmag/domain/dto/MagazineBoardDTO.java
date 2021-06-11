package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.account.Company;
import lombok.Data;

@Data
public class MagazineBoardDTO {
    private Long magazineBoardSeq;

    private String name;

    private String boardImgUrl;

    private String description;

    private Integer pricePerMonth;

    private String category;

    private Company company;
}
