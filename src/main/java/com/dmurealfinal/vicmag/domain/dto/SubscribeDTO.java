package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.magazineboard.MagazineBoard;
import com.dmurealfinal.vicmag.domain.entity.purchase.Purchase;
import com.dmurealfinal.vicmag.domain.entity.purchase.Subscribe;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class SubscribeDTO {
    private Long purchaseSeq;

    private PurchaseDTO purchase;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDateTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDateTime;

    private Double rating;

    private Long magazineBoardSeq;

    public SubscribeDTO() {}

    @Builder
    public SubscribeDTO(Long purchaseSeq, PurchaseDTO purchase, LocalDateTime startDateTime, LocalDateTime endDateTime, Double rating, Long magazineBoardSeq) {
        this.purchaseSeq = purchaseSeq;
        this.purchase = purchase;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.rating = rating;
        this.magazineBoardSeq = magazineBoardSeq;
    }

    public Subscribe toEntity() {
        return Subscribe.builder()
                .purchaseSeq(this.purchaseSeq)
                .startDateTime(this.startDateTime)
                .endDateTime(this.endDateTime)
                .rating(this.rating)
                .magazineBoardSeq(this.magazineBoardSeq)
                .build();
    }
}
