package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.account.Company;
import com.dmurealfinal.vicmag.domain.entity.magazine.Magazine;
import com.dmurealfinal.vicmag.domain.entity.magazineboard.MagazineBoard;
import lombok.Builder;
import lombok.Data;

import java.util.List;

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

    private List<MagazineDTO> magazineList;

    public MagazineBoardDTO() {}

    @Builder
    public MagazineBoardDTO(Long magazineBoardSeq, String name, String boardImgUrl, String description, Integer pricePerMonth, String category, CompanyDTO company, List<MagazineDTO> magazineList) {
        this.magazineBoardSeq = magazineBoardSeq;
        this.name = name;
        this.boardImgUrl = boardImgUrl;
        this.description = description;
        this.pricePerMonth = pricePerMonth;
        this.category = category;
        this.company = company;
        this.magazineList = magazineList;
    }

    public MagazineBoard toEntity() {
        return MagazineBoard.builder()
                .magazineBoardSeq(this.magazineBoardSeq)
                .name(this.name)
                .boardImgUrl(this.boardImgUrl)
                .description(this.description)
                .pricePerMonth(this.pricePerMonth)
                .category(this.category)
                .company((this.company == null ? null : this.company.toEntity()))
                .build();
    }
}
