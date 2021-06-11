package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.magazine.Magazine;
import com.dmurealfinal.vicmag.domain.entity.purchase.Purchase;
import com.dmurealfinal.vicmag.domain.entity.purchase.SinglePurchase;
import lombok.Data;


@Data
public class SinglePurchaseDTO {

    private Long purchaseSeq;

    private PurchaseDTO purchase;

    private Double rating;

    private Long magazineSeq;

    public SinglePurchase toEntity() {
        return SinglePurchase.builder()
                .purchaseSeq(this.purchaseSeq)
                .rating(this.rating)
                .magazineSeq(this.magazineSeq)
                .build();
    }
}
