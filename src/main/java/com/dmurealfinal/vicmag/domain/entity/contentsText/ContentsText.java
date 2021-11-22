package com.dmurealfinal.vicmag.domain.entity.contentsText;

import com.dmurealfinal.vicmag.domain.dto.ContentsTextDTO;
import com.dmurealfinal.vicmag.domain.dto.MagazineContentsDTO;
import com.dmurealfinal.vicmag.domain.entity.magazinecontents.MagazineContents;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Table(name = "TB_CONTENTS_TEXT")
@Entity
public class ContentsText {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentsTextSeq;

    @Column
    private Integer startTime;

    @Column
    private Integer endTime;

    @Column(length = 1000)
    private String text;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "magazineContentsSeq")
    private MagazineContents magazineContents;

    public ContentsText() {}

    @Builder
    public ContentsText(Long contentsTextSeq, Integer startTime, Integer endTime, String text, MagazineContents magazineContents) {
        this.contentsTextSeq = contentsTextSeq;
        this.startTime = startTime;
        this.endTime = endTime;
        this.text = text;
        this. magazineContents = magazineContents;
    }

    public ContentsTextDTO toDTO() {
        return ContentsTextDTO.builder()
                .contentsTextSeq(this.contentsTextSeq)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .text(this.text)
                .magazineContents(this.magazineContents.toDTO())
                .build();
    }

    public static List<ContentsTextDTO> toDTOList (List<ContentsText> entityList) {
        return entityList.stream().map(entity->entity.toDTO()).collect(Collectors.toList());
    }
}
