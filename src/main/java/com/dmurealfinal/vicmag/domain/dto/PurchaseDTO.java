package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.purchase.Purchase;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PurchaseDTO {
    private Long purchaseSeq;

    private String purchaseType;

    private String userId;

    private String companyId;


    // for response
    private UserDTO user;

    private CompanyDTO company;

    private SinglePurchaseDTO singlePurchase;

    private SubscribeDTO subscribe;

    private PaymentDTO payment;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdDateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime modifiedDateTime;


    public PurchaseDTO() {}

    @Builder
    public PurchaseDTO(Long purchaseSeq, String purchaseType, String userId, String companyId, UserDTO user, CompanyDTO company, SinglePurchaseDTO singlePurchase, SubscribeDTO subscribe, PaymentDTO payment, LocalDateTime createdDateTime, LocalDateTime modifiedDateTime) {
        this.purchaseSeq = purchaseSeq;
        this.purchaseType = purchaseType;
        this.userId = userId;
        this.companyId = companyId;
        this.user = user;
        this.company = company;
        this.singlePurchase = singlePurchase;
        this.subscribe = subscribe;
        this.payment = payment;
        this.createdDateTime = createdDateTime;
        this.modifiedDateTime = modifiedDateTime;
    }

    public Purchase toEntity() {
        return Purchase.builder()
                .purchaseSeq(this.purchaseSeq)
                .purchaseType(this.purchaseType)
                .userId(this.userId)
                .companyId(this.companyId)
                .build();
    }
}
