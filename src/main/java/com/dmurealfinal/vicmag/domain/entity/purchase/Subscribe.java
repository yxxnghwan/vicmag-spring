package com.dmurealfinal.vicmag.domain.entity.purchase;

import com.dmurealfinal.vicmag.domain.entity.magazineboard.MagazineBoard;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table(name = "TB_SUBSCRIBE")
@Entity
public class Subscribe {
    @Id
    private Long purchaseSeq;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Purchase purchase;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "datetime default now()")
    private LocalDateTime startDateTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "datetime", nullable = false)
    private LocalDateTime endDateTime;

    @Column(columnDefinition = "double")
    private Double rating;

    private Long magazineBoardSeq;

    public Subscribe() {}

    @Builder
    public Subscribe(Long purchaseSeq, Purchase purchase, LocalDateTime startDateTime, LocalDateTime endDateTime, Double rating, Long magazineBoardSeq) {
        this.purchaseSeq = purchaseSeq;
        this.purchase = purchase;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.rating = rating;
        this.magazineBoardSeq = magazineBoardSeq;
    }
}
