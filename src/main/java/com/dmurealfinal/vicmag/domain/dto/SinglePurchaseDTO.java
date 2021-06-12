package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.magazine.Magazine;
import com.dmurealfinal.vicmag.domain.entity.purchase.Purchase;
import com.dmurealfinal.vicmag.domain.entity.purchase.SinglePurchase;
import lombok.Builder;
import lombok.Data;


@Data
public class SinglePurchaseDTO {

    private Long purchaseSeq;

    private PurchaseDTO purchase;

    private Double rating;

    private Long magazineSeq;

    public SinglePurchaseDTO() {}

    @Builder
    public SinglePurchaseDTO(Long purchaseSeq, PurchaseDTO purchase, Double rating, Long magazineSeq) {
        this.purchaseSeq = purchaseSeq;
        this.purchase = purchase;
        this.rating = rating;
        this.magazineSeq = magazineSeq;
    }

    public SinglePurchase toEntity() {
        return SinglePurchase.builder()
                .purchaseSeq(this.purchaseSeq)
                .rating(this.rating)
                .magazineSeq(this.magazineSeq)
                .build();
    }
}
