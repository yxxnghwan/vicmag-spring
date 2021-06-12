package com.dmurealfinal.vicmag.domain.entity.magazineboard;

import com.dmurealfinal.vicmag.domain.dto.MagazineBoardDTO;
import com.dmurealfinal.vicmag.domain.dto.MagazineDTO;
import com.dmurealfinal.vicmag.domain.entity.account.Company;
import com.dmurealfinal.vicmag.domain.entity.magazine.Magazine;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Table(name = "TB_MAGAZINE_BOARD")
@Entity
public class MagazineBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long magazineBoardSeq;

    @Column(columnDefinition = "nvarchar(30)", nullable = false)
    private String name;

    @Column(columnDefinition = "nvarchar(300)")
    private String boardImgUrl;

    @Column(columnDefinition = "nvarchar(1500)")
    private String description;

    @Column(columnDefinition = "int", nullable = false)
    private Integer pricePerMonth;

    @Column(columnDefinition = "nvarchar(20)")
    private String category;

    @ManyToOne
    @JoinColumn(name = "companyId")
    private Company company;

    public MagazineBoard() {}

    @Builder
    public MagazineBoard(Long magazineBoardSeq, String name, String boardImgUrl, String description, Integer pricePerMonth, String category, Company company) {
        this.magazineBoardSeq = magazineBoardSeq;
        this.name = name;
        this.boardImgUrl = boardImgUrl;
        this.description = description;
        this.pricePerMonth = pricePerMonth;
        this.category = category;
        this.company = company;
    }

    public MagazineBoardDTO toDTO() {
        return MagazineBoardDTO.builder()
                .magazineBoardSeq(this.magazineBoardSeq)
                .name(this.name)
                .boardImgUrl(this.boardImgUrl)
                .description(this.description)
                .pricePerMonth(this.pricePerMonth)
                .category(this.category)
                .build();
    }

    public static List<MagazineBoardDTO> toDTOList (List<MagazineBoard> entityList) {
        List<MagazineBoardDTO> result = new ArrayList<>();
        for(MagazineBoard entity : entityList) {
            result.add(entity.toDTO());
        }
        return result;
    }
}
