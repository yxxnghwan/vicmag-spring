package com.dmurealfinal.vicmag.domain.entity.magazinecontents;

import com.dmurealfinal.vicmag.domain.entity.magazine.Magazine;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Table(name = "TB_MAGAZINE_CONTENTS")
@Entity
public class MagazineContents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long magazineContentsSeq;

    @Column(nullable = false)
    private Integer page;

    @Column(columnDefinition = "nvarchar(10)", nullable = false)
    private String contentsType;

    @Column(columnDefinition = "nvarchar(300)", nullable = false)
    private String contentsUrl;

    @Column(columnDefinition = "nvarchar(300)")
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "magazineSeq")
    private Magazine magazine;

    public MagazineContents() {}

    @Builder
    public MagazineContents(Integer page, String contentsType, String contentsUrl, String description, Magazine magazine) {
        this.page = page;
        this.contentsType = contentsType;
        this.contentsUrl = contentsUrl;
        this.description = description;
        this.magazine = magazine;
    }
}
