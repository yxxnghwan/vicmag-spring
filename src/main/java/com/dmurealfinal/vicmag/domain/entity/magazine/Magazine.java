package com.dmurealfinal.vicmag.domain.entity.magazine;

import com.dmurealfinal.vicmag.domain.BaseTimeEntity;
import com.dmurealfinal.vicmag.domain.dto.MagazineDTO;
import com.dmurealfinal.vicmag.domain.dto.UserDTO;
import com.dmurealfinal.vicmag.domain.entity.account.User;
import com.dmurealfinal.vicmag.domain.entity.magazineboard.MagazineBoard;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Table(name = "TB_MAGAZINE")
@Entity
public class Magazine extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long magazineSeq;

    @Column(length = 60, nullable = false)
    private String name;

    @Column(length = 300)
    private String coverImgUrl;

    @Column(columnDefinition = "int", nullable = false)
    private Integer price;

    @Column(length = 1000)
    private String tag;

    @Column(length = 300)
    private String bgmUrl;

    @ManyToOne
    @JoinColumn(name = "boardSeq")
    private MagazineBoard board;

    public Magazine() {}

    @Builder
    public Magazine(Long magazineSeq, String name, String coverImgUrl, Integer price, String tag, String bgmUrl, MagazineBoard board, LocalDateTime createdDateTime, LocalDateTime modifiedDateTime) {
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

    public MagazineDTO toDTO() {
        return MagazineDTO.builder()
                .magazineSeq(this.magazineSeq)
                .name(this.name)
                .coverImgUrl(this.coverImgUrl)
                .price(this.price)
                .tag(this.tag)
                .bgmUrl(this.bgmUrl)
                .createdDateTime(this.createdDateTime)
                .modifiedDateTime(this.modifiedDateTime)
                .build();
    }

    public static List<MagazineDTO> toDTOList (List<Magazine> entityList) {
        return entityList.stream().map(entity->entity.toDTO()).collect(Collectors.toList());
    }
}
