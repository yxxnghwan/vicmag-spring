package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.contentsText.ContentsText;
import com.dmurealfinal.vicmag.domain.entity.magazinecontents.MagazineContents;
import lombok.Builder;
import lombok.Data;

@Data
public class ContentsTextDTO {
    private Long contentsTextSeq;

    private Integer startTime;

    private Integer endTime;

    private String text;

    private MagazineContentsDTO magazineContents;

    public ContentsTextDTO() {}

    @Builder
    public ContentsTextDTO(Long contentsTextSeq, Integer startTime, Integer endTime, String text, MagazineContentsDTO magazineContents) {
        this.contentsTextSeq = contentsTextSeq;
        this.startTime = startTime;
        this.endTime = endTime;
        this.text = text;
        this.magazineContents = magazineContents;
    }

    public ContentsText toEntity() {
        return ContentsText.builder()
                .contentsTextSeq(this.contentsTextSeq)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .text(this.text)
                .magazineContents((this.magazineContents == null ? null : this.magazineContents.toEntity()))
                .build();
    }
}
