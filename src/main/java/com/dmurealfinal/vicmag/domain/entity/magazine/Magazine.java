package com.dmurealfinal.vicmag.domain.entity.magazine;

import com.dmurealfinal.vicmag.domain.BaseTimeEntity;
import com.dmurealfinal.vicmag.domain.entity.magazineboard.MagazineBoard;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Table(name = "TB_MAGAZINE")
@Entity
public class Magazine extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long magazineSeq;

    @Column(columnDefinition = "nvarchar(30)", nullable = false)
    private String name;

    @Column(columnDefinition = "nvarchar(300)")
    private String coverImgUrl;

    @Column(columnDefinition = "int", nullable = false)
    private Integer price;

    @Column(columnDefinition = "nvarchar(1000)")
    private String tag;

    @Column(columnDefinition = "nvarchar(300)")
    private String bgmUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "boardSeq")
    private MagazineBoard board;

    public Magazine() {}

    @Builder
    public Magazine(String name, String coverImgUrl, Integer price, String tag, String bgmUrl, MagazineBoard board) {
        this.name = name;
        this.coverImgUrl = coverImgUrl;
        this.price = price;
        this.tag = tag;
        this.bgmUrl = bgmUrl;
        this.board = board;
    }
}
