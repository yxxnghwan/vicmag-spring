package com.dmurealfinal.vicmag.domain.entity.magazinecontents;

import com.dmurealfinal.vicmag.domain.dto.MagazineBoardDTO;
import com.dmurealfinal.vicmag.domain.dto.MagazineContentsDTO;
import com.dmurealfinal.vicmag.domain.entity.magazine.Magazine;
import com.dmurealfinal.vicmag.domain.entity.magazineboard.MagazineBoard;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "magazineSeq")
    private Magazine magazine;

    public MagazineContents() {}

    @Builder
    public MagazineContents(Long magazineContentsSeq, Integer page, String contentsType, String contentsUrl, String description, Magazine magazine) {
        this.magazineContentsSeq = magazineContentsSeq;
        this.page = page;
        this.contentsType = contentsType;
        this.contentsUrl = contentsUrl;
        this.description = description;
        this.magazine = magazine;
    }

    public MagazineContentsDTO toDTO() {
        return MagazineContentsDTO.builder()
                .magazineContentsSeq(this.magazineContentsSeq)
                .page(this.page)
                .contentsType(this.contentsType)
                .contentsUrl(this.contentsUrl)
                .description(this.description)
                .build();
    }

    public static List<MagazineContentsDTO> toDTOList (List<MagazineContents> entityList) {
        List<MagazineContentsDTO> result = new ArrayList<>();
        for(MagazineContents entity : entityList) {
            result.add(entity.toDTO());
        }
        return result;
    }
}
