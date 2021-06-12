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

    private String paymentType;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime payDateTime;

    // for response
    private PurchaseDTO purchase;

    public PaymentDTO() {}

    @Builder
    public PaymentDTO(Long purchaseSeq, Integer totalPrice, String description, String paymentType, LocalDateTime payDateTime, PurchaseDTO purchase) {
        this.purchaseSeq = purchaseSeq;
        this.totalPrice = totalPrice;
        this.description = description;
        this.paymentType = paymentType;
        this.payDateTime = payDateTime;
        this.purchase = purchase;
    }

    public Payment toEntity() {
        return Payment.builder()
                .purchaseSeq(this.purchaseSeq)
                .totalPrice(this.totalPrice)
                .description(this.description)
                .paymentType(this.paymentType)
                .payDateTime(this.payDateTime)
                .build();
    }
}
