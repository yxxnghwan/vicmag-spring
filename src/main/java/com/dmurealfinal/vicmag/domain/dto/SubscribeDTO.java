package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.magazineboard.MagazineBoard;
import com.dmurealfinal.vicmag.domain.entity.purchase.Purchase;
import com.dmurealfinal.vicmag.domain.entity.purchase.Subscribe;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubscribeDTO {
    private Long purchaseSeq;

    private PurchaseDTO purchase;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime startDateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
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
