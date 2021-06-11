package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.account.Company;
import com.dmurealfinal.vicmag.domain.entity.magazineboard.MagazineBoard;
import lombok.Data;

@Data
public class MagazineBoardDTO {
    private Long magazineBoardSeq;

    private String name;

    private String boardImgUrl;

    private String description;

    private Integer pricePerMonth;

    private String category;

    // for response
    private CompanyDTO company;

    public MagazineBoard toEntity() {
        return MagazineBoard.builder()
                .name(this.name)
                .boardImgUrl(this.boardImgUrl)
                .description(this.description)
                .pricePerMonth(this.pricePerMonth)
                .category(this.category)
                .company(this.company.toEntity())
                .build();
    }
}
