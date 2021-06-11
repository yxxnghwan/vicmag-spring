package com.dmurealfinal.vicmag.domain.entity.magazineboard;

import com.dmurealfinal.vicmag.domain.entity.account.Company;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "companyId")
    private Company company;

    public MagazineBoard() {}

    @Builder
    public MagazineBoard(String name, String boardImgUrl, String description, Integer pricePerMonth, String category, Company company) {
        this.name = name;
        this.boardImgUrl = boardImgUrl;
        this.description = description;
        this.pricePerMonth = pricePerMonth;
        this.category = category;
        this.company = company;
    }
}
