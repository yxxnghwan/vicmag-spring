package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.purchase.Payment;
import com.dmurealfinal.vicmag.domain.entity.purchase.Purchase;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class PaymentDTO {
    private Long purchaseSeq;

    private Integer totalPrice;

    private String description;

    private String pg;

    private String paymentType;

    // for response
    private PurchaseDTO purchase;

    public PaymentDTO() {}

    @Builder
    public PaymentDTO(Long purchaseSeq, Integer totalPrice, String description, String pg, String paymentType, PurchaseDTO purchase) {
        this.purchaseSeq = purchaseSeq;
        this.totalPrice = totalPrice;
        this.description = description;
        this.paymentType = paymentType;
        this.purchase = purchase;
        this.pg = pg;
    }

    public Payment toEntity() {
        return Payment.builder()
                .purchaseSeq(this.purchaseSeq)
                .totalPrice(this.totalPrice)
                .description(this.description)
                .pg(this.pg)
                .paymentType(this.paymentType)
                .build();
    }
}
