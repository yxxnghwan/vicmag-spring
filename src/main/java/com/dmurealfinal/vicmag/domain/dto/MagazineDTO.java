package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.magazine.Magazine;
import com.dmurealfinal.vicmag.domain.entity.magazineboard.MagazineBoard;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MagazineDTO {
    private Long magazineSeq;

    private String name;

    private String coverImgUrl;

    private Integer price;

    private String tag;

    private String bgmUrl;

    // for response
    private MagazineBoardDTO board;

    private List<MagazineContentsDTO> magazineContentsList;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdDateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime modifiedDateTime;

    public MagazineDTO() {}

    @Builder
    public MagazineDTO(Long magazineSeq, String name, String coverImgUrl, Integer price, String tag, String bgmUrl, MagazineBoardDTO board, LocalDateTime createdDateTime, LocalDateTime modifiedDateTime) {
        this.magazineSeq = magazineSeq;
        this.name = name;
        this.coverImgUrl = coverImgUrl;
        this.price = price;
        this.tag = tag;
        this.bgmUrl = bgmUrl;
        this.board = board;
        this.createdDateTime = createdDateTime;
        this.modifiedDateTime = modifiedDateTime;
    }

    public Magazine toEntity() {
        return Magazine.builder()
                .magazineSeq(this.magazineSeq)
                .name(this.name)
                .coverImgUrl(this.coverImgUrl)
                .price(this.price)
                .tag(this.tag)
                .bgmUrl(this.bgmUrl)
                .board((this.board == null ? null : this.board.toEntity()))
                .build();
    }
}
