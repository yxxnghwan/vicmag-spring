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
import java.util.stream.Collectors;

@Getter
@Table(name = "TB_MAGAZINE_CONTENTS")
@Entity
public class MagazineContents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long magazineContentsSeq;

    @Column(nullable = false)
    private Integer page;

    @Column(length = 10, nullable = false)
    private String contentsType;

    @Column(length = 300, nullable = false)
    private String contentsUrl;

    @Column(length = 300)
    private String description;

    @Column(length = 300)
    private String contentsThumbnailUrl;

    @Column(columnDefinition = "TINYINT")
    private Integer uploadStatus;

    @ManyToOne
    @JoinColumn(name = "magazineSeq")
    private Magazine magazine;

    public MagazineContents() {}

    @Builder
    public MagazineContents(Long magazineContentsSeq, Integer page, String contentsType, String contentsUrl, String description, String contentsThumbnailUrl, Integer uploadStatus,Magazine magazine) {
        this.magazineContentsSeq = magazineContentsSeq;
        this.page = page;
        this.contentsType = contentsType;
        this.contentsUrl = contentsUrl;
        this.description = description;
        this.contentsThumbnailUrl = contentsThumbnailUrl;
        this.uploadStatus = uploadStatus;
        this.magazine = magazine;
    }

    public MagazineContentsDTO toDTO() {
        return MagazineContentsDTO.builder()
                .magazineContentsSeq(this.magazineContentsSeq)
                .page(this.page)
                .contentsType(this.contentsType)
                .contentsUrl(this.contentsUrl)
                .description(this.description)
                .contentsThumbnailUrl(this.contentsThumbnailUrl)
                .uploadStatus(this.uploadStatus)
                .build();
    }

    public static List<MagazineContentsDTO> toDTOList (List<MagazineContents> entityList) {
        return entityList.stream().map(entity->entity.toDTO()).collect(Collectors.toList());
    }
}
