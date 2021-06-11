package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.account.Company;
import com.dmurealfinal.vicmag.domain.entity.account.User;
import com.dmurealfinal.vicmag.domain.entity.purchase.Payment;
import com.dmurealfinal.vicmag.domain.entity.purchase.Purchase;
import com.dmurealfinal.vicmag.domain.entity.purchase.SinglePurchase;
import com.dmurealfinal.vicmag.domain.entity.purchase.Subscribe;
import lombok.Data;

import javax.persistence.Column;

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

    public Purchase toEntity() {
        return Purchase.builder()
                .purchaseType(this.purchaseType)
                .userId(this.userId)
                .companyId(this.companyId)
                .build();
    }
}
