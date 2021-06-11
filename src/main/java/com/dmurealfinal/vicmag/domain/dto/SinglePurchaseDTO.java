package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.magazine.Magazine;
import com.dmurealfinal.vicmag.domain.entity.purchase.Purchase;
import lombok.Data;


@Data
public class SinglePurchaseDTO {

    private Long purchaseSeq;

    private Purchase purchase;

    private Double rating;

    private Magazine magazine;
}
