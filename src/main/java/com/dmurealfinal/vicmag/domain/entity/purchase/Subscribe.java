package com.dmurealfinal.vicmag.domain.entity.purchase;

import com.dmurealfinal.vicmag.domain.dto.SinglePurchaseDTO;
import com.dmurealfinal.vicmag.domain.dto.SubscribeDTO;
import com.dmurealfinal.vicmag.domain.entity.magazineboard.MagazineBoard;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Table(name = "TB_SUBSCRIBE")
@Entity
public class Subscribe {
    @Id
    private Long purchaseSeq;

    @OneToOne
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

    @Column
    private Long magazineBoardSeq;

    public Subscribe() {}

    @Builder
    public Subscribe(Long purchaseSeq, LocalDateTime startDateTime, LocalDateTime endDateTime, Double rating, Long magazineBoardSeq) {
        this.purchaseSeq = purchaseSeq;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.rating = rating;
        this.magazineBoardSeq = magazineBoardSeq;
    }

    public SubscribeDTO toDTO() {
        return SubscribeDTO.builder()
                .purchaseSeq(this.purchaseSeq)
                .startDateTime(this.startDateTime)
                .endDateTime(this.endDateTime)
                .rating(this.rating)
                .magazineBoardSeq(this.magazineBoardSeq)
                .build();
    }

    public static List<SubscribeDTO> toDTOList (List<Subscribe> entityList) {
        List<SubscribeDTO> result = new ArrayList<>();
        for(Subscribe entity : entityList) {
            result.add(entity.toDTO());
        }
        return result;
    }
}
